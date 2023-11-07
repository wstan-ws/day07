import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerHandler implements Runnable {
    
    private final Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() throws Exception {
        
        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);

        boolean isLooping = true;

        while (isLooping) {

            String line = br.readLine();
            String[] numAndOps = line.split(" ");

            double lastNum = Double.parseDouble(numAndOps[numAndOps.length - 2]);
            double secondLastNum = Double.parseDouble(numAndOps[numAndOps.length - 3]);
            Calculator cal = new Calculator(lastNum, secondLastNum);

            String result = "";
            List<String> finalResult = new ArrayList<>();
            
            switch (numAndOps[numAndOps.length - 1]) {
                case "+":
                    System.out.printf("Addition of %.1f, %.1f\n", lastNum, secondLastNum);
                    result = cal.add();
                    for (int i = 0; i < numAndOps.length - 3; i++) {
                        finalResult.add(numAndOps[i]);
                    }
                    finalResult.add(result);
                    result = String.join(" ", finalResult);
                    break;
                case "-":
                    System.out.printf("Subtraction of %.1f, %.1f\n", lastNum, secondLastNum);
                    result = cal.subtract();
                    for (int i = 0; i < numAndOps.length - 3; i++) {
                        finalResult.add(numAndOps[i]);
                    }
                    finalResult.add(result);
                    result = String.join(" ", finalResult);
                    break;
                case "*":
                    System.out.printf("Multiplication of %.1f, %.1f\n", lastNum, secondLastNum);
                    result = cal.multiply();
                    for (int i = 0; i < numAndOps.length - 3; i++) {
                        finalResult.add(numAndOps[i]);
                    }
                    finalResult.add(result);
                    result = String.join(" ", finalResult);
                    break;
                case "/":
                    System.out.printf("Division of %.1f, %.1f\n", lastNum, secondLastNum);
                    result = cal.divide();
                    for (int i = 0; i < numAndOps.length - 3; i++) {
                        finalResult.add(numAndOps[i]);
                    }
                    finalResult.add(result);
                    result = String.join(" ", finalResult);
                    break;
                case "end":
                    isLooping = false;
                    break;
                default:
                    System.out.println("Only numbers entered");
                    result = line;
            }

            bw.write(result);
            bw.newLine();
            bw.flush();
            
        }
    }
}