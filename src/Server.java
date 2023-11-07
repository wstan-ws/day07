import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) throws Exception {
        
        int port = 3000;

        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        ServerSocket server = new ServerSocket(port);
        System.out.printf("Opening server on port %d\n", port);

        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        while (true) {
            System.out.println("Waiting for connection...");
            Socket client = server.accept();
            System.out.printf("Connected to client on port %d\n", port);

            Runnable serverHandler = new ServerHandler(client);
            threadPool.submit(serverHandler);
        }
    }
}
