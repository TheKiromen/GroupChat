import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

public class ServerListener implements Runnable {

    //Variables
    private Socket server;
    private BufferedReader readFromServer;

    //Constructor, setting up input stream from server
    ServerListener(Socket sv) throws IOException {
        this.server=sv;
        readFromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
    }

    @Override
    public void run() {
        try {
            //Main loop for listening for messages
            while(true){
                //Read and print out message
                String message = readFromServer.readLine();
                System.out.println(message);
            }
        }//Exception when server closes
        catch(SocketException e){
            System.err.println("Disconnected from the server.");
        }
        catch (IOException e) {
            e.printStackTrace();
        }//Cleanup
        finally {
            try {
                readFromServer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
