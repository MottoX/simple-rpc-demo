package net.mottox.rpc.server;

import java.io.IOException;

import net.mottox.rpc.framework.RpcFramework;
import net.mottox.rpc.server.service.ArithmeticService;
import net.mottox.rpc.server.service.impl.ArithmeticServiceImpl;

/**
 * @author Weixin(Robin) Wang
 *         Created on 16/8/9.
 */
public class RpcProvider {
    private static final String HOSTNAME = "localhost";
    private static final int PORT = 8088;

    public static void main(String[] args) throws IOException {
        ArithmeticService service = new ArithmeticServiceImpl();
        RpcFramework.exportService(service, HOSTNAME, PORT);
    }
}
