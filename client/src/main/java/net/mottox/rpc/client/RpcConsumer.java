package net.mottox.rpc.client;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import net.mottox.rpc.client.service.ArithmeticService;
import net.mottox.rpc.framework.RpcFramework;

/**
 * @author Weixin(Robin) Wang
 *         Created on 16/8/9.
 */
public class RpcConsumer {
    private static final String HOSTNAME = "localhost";
    private static final int PORT = 8088;
    private static final Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        ArithmeticService service = RpcFramework.refer(ArithmeticService.class, HOSTNAME, PORT);
        while (true) {
            int x = random.nextInt();
            int y = random.nextInt();
            int s = service.sum(x, y);

            System.out.println("------------------------------------------");
            String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
            System.out.println(currentTime);
            System.out.println(x + " + " + y + " = " + s);
            System.out.println("------------------------------------------");

            Thread.sleep(1000L);
        }
    }
}
