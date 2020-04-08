import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class ClientHandler implements Runnable {

    //Variables
    String username;
    private int chatroom;
    private ConcurrentHashMap<Integer,ArrayList<ClientHandler>> users;
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
    public ClientHandler(Socket clientSocket, ConcurrentHashMap<Integer,ArrayList<ClientHandler>> users, int chatroom) throws IOException{
        this.client = clientSocket;
        this.users = users;
        this.chatroom=chatroom;

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
            //Send message about new connection to chatroom
            Message msg=(Message)inFromClient.readObject();
            username=msg.getSender();
            sendMessageToAll(new Message("[Server]",username+" joined!"));

            //Main loop for broadcasting messages
            while(true){
                //Listen for message and send it to all clients in your chatroom
                msg = (Message)inFromClient.readObject();
                System.out.println(msg.getSender()+" sent: " + msg.getContent());
                sendMessageToAll(msg);
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
     * Safely sends message to this client.
     * @param msg Message you want to send.
     * @throws IOException When stream error occurs.
     */
    //Method for safe message sending from inside and outside of the Thread
    public void sendMessage(Message msg) throws IOException {
        outToClient.writeObject(msg);
        outToClient.flush();
    }

    /**
     * Safely sends message to all clients in chatroom.
     * @param msg Message you want to send.
     * @throws IOException When stream error occurs.
     */
    //Method for safe message broadcasting from inside and outside the Thread
    public void sendMessageToAll(Message msg) throws IOException{
        for(ClientHandler u : users.get(chatroom)){
            u.sendMessage(msg);
        }
    }

}
