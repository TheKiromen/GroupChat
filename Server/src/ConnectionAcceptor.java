import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ConnectionAcceptor implements Runnable{

    //Variables
    private final ServerSocket server;
    private final Chatroom globalChat;
    private final ArrayList<User> clients;

    private Socket connection;
    private User client;
    private Thread messages;


    public ConnectionAcceptor(ServerSocket serverSocket, Chatroom chatroom, ArrayList<User> clients){
        this.server=serverSocket;
        this.globalChat=chatroom;
        this.clients=clients;
    }

    //Listens for new connections and handles them
    public void run(){
        while(true){
            try {
                connection=server.accept();
                System.out.println("Server connected to new client"); //todo: add name of the client
                //TODO get nickname from user's message object
                //TODO Use objectInputStream to get initial message from client;
                //Creates new user and adds him to the list
                client = new User("Jan",connection,globalChat);
                clients.add(client);

                //New Thread for each User, responsible for sending and receiving messages
                messages=new Thread(new MessagesHandler(client,clients));
                messages.start();
            } catch (IOException e) {
                System.out.println("Connection error:");
                System.out.println(e.getMessage());
            }
        }
    }

}
