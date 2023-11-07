import java.net.Socket;

public class Client {
    
    public static void main(String[] args) throws Exception {
        
        int port = 3000;

        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        Socket socket = new Socket("localhost", port);
        System.out.printf("Connected to server on port %d\n", port);      

        ClientHandler clientHandler = new ClientHandler(socket);
        clientHandler.start();
    }
}