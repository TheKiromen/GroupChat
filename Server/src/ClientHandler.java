import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {

    //TODO Try sending objects instead of strings



    //Variables
    private ArrayList<ClientHandler> users;
    private Socket client;
    private PrintWriter writeToClient;
    private BufferedReader readFromClient;


    //Constructor, setting up communication streams
    public ClientHandler(Socket clientSocket, ArrayList<ClientHandler> users) throws IOException {
        this.client = clientSocket;
        this.users = users;
        writeToClient = new PrintWriter(client.getOutputStream(),true);
        readFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }

    @Override
    public void run() {
        try{
            while(true){
                //Listen for message and send it to all clients
                String message = readFromClient.readLine();
                for(ClientHandler u : users){
                    u.sendMessage(message);
                }
            }
        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }//Cleanup
        finally{
            writeToClient.close();
            try {
                readFromClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Method for safe message sending from outside the thread
    public void sendMessage(String message){
       writeToClient.println(message);
    }

}
