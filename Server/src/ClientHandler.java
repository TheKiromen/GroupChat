import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket client;
    private PrintWriter writeToClient;
    private BufferedReader readFromClient;

    public ClientHandler(Socket clientSocket) throws IOException {
        this.client = clientSocket;
        writeToClient = new PrintWriter(client.getOutputStream(),true);
        readFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }

    @Override
    public void run() {
        try{
            while(true){
                String message = readFromClient.readLine();
                System.out.println(message);
                writeToClient.println("Server received your message!");
            }
        }catch(IOException e){
            System.err.println(e.getMessage());
        }finally{
            writeToClient.close();
            try {
                readFromClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
