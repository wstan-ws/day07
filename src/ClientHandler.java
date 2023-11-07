import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientHandler {
    
    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void start() throws Exception {
        
        Console cons = System.console();

        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);

        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        boolean isLooping = true;
        String result = "";

        while (isLooping) {
            String line = cons.readLine("> ");
            line = line.toLowerCase();

            if (line.equals("end")) {
                isLooping = false;
                break;
            }

            line = result + " " + line;
            line = line.trim();

            bw.write(line);
            bw.newLine();
            bw.flush();

            result = br.readLine();
            result = result.trim();
            System.out.println(result);
        }   
    }
}