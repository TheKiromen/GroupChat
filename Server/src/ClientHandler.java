import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {

    //Variables
    private ArrayList<ClientHandler> users;
    private Socket client;
    private ObjectInputStream inFromClient;
    private ObjectOutputStream outToClient;


    //Constructor, setting up communication streams
    public ClientHandler(Socket clientSocket, ArrayList<ClientHandler> users) throws IOException {
        this.client = clientSocket;
        this.users = users;

        inFromClient = new ObjectInputStream(clientSocket.getInputStream());
        outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
    }

    @Override
    public void run() {
        try{
            while(true){
                //Listen for message and send it to all clients
                Message msg = (Message)inFromClient.readObject();
                System.out.println(msg.getSender()+" sent message.");
                for(ClientHandler u : users){
                    u.sendMessage(msg);
                }
            }
        }
        catch(IOException | ClassNotFoundException e){
            System.err.println(e.getMessage());
        }//Cleanup
        finally{
            try {
                inFromClient.close();
                outToClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Method for safe message sending from outside the thread
    public void sendMessage(Message msg) throws IOException {
        outToClient.writeObject(msg);
        outToClient.flush();
    }

}
