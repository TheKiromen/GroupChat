import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {

    //Variables
    private ArrayList<ClientHandler> users;
    private Socket client;
    private ObjectInputStream inFromClient;
    private ObjectOutputStream outToClient;


    /**
     * Creates new thread responsible for communication with specified client
     * @param clientSocket client Socket you want communicate with
     * @param users list of users in chatroom.
     * @throws IOException When cant establish stream connection with client.
     */
    //Constructor, setting up communication streams
    public ClientHandler(Socket clientSocket, ArrayList<ClientHandler> users) throws IOException {
        this.client = clientSocket;
        this.users = users;

        inFromClient = new ObjectInputStream(client.getInputStream());
        outToClient = new ObjectOutputStream(client.getOutputStream());
    }

    /**
     * Main method responsible for receiving and sending messages.
     * Sends received message to all users in chatroom.
     */
    @Override
    public void run() {
        try{
            while(true){
                //Listen for message and send it to all clients
                Message msg = (Message)inFromClient.readObject();
                System.out.println(msg.getSender()+" sent: " + msg.getContent());
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

    /**
     * Safely sends message to this client from outside of this class.
     * @param msg Message you want to send.
     * @throws IOException When stream error occurs.
     */
    //Method for safe message sending from outside the thread
    public void sendMessage(Message msg) throws IOException {
        outToClient.writeObject(msg);
        outToClient.flush();
    }

}
