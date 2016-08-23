package com.github.mottox.rpc.framework;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author Weixin(Robin) Wang
 *         Created on 16/8/9.
 */
public class RpcFramework {
    private static final Executor EXECUTOR = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static void exportService(Object service, String hostname, int port) throws IOException {
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(hostname, port));
        try {
            while (true) {
                EXECUTOR.execute(new ExporterTask(service, server.accept()));
            }
        } finally {
            server.close();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T refer(Class<T> interfaceClazz, String hostname, int port) {
        return (T) Proxy.newProxyInstance(interfaceClazz.getClassLoader(), new Class<?>[] {interfaceClazz},
                (proxy, method, args) -> {
                    try (Socket socket = new Socket(hostname, port)) {
                        try (ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())) {
                            outputStream.writeUTF(method.getName());
                            outputStream.writeObject(method.getParameterTypes());
                            outputStream.writeObject(args);
                            try (ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())) {
                                Object result = inputStream.readObject();
                                if (result instanceof Throwable) {
                                    throw (Throwable) result;
                                }
                                return result;
                            }
                        }
                    }
                });
    }

    private static class ExporterTask implements Runnable {
        private final Object service;
        private final Socket socket;

        ExporterTask(Object service, Socket socket) {
            this.service = service;
            System.out.println(socket.toString());
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                try (ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())) {
                    String methodName = inputStream.readUTF();
                    Class<?>[] parameterTypes = (Class<?>[]) inputStream.readObject();
                    Object[] arguments = (Object[]) inputStream.readObject();
                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                    try {
                        Method method = service.getClass().getMethod(methodName, parameterTypes);
                        Object result = method.invoke(service, arguments);
                        outputStream.writeObject(result);
                    } catch (IOException e) {
                        outputStream.writeObject(e);
                    } finally {
                        outputStream.close();
                    }
                } finally {
                    socket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
