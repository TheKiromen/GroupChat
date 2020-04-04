import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ConnectionAcceptor implements Runnable{

    //Variables
    private final ServerSocket server;
    private final Chatroom globalChat;
    private final ArrayList<User> clients;

    private InitialMessage init;
    private ObjectInputStream inFromClient;
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
        System.out.println("Server is waiting for client connection...");

        //Main connections loop
        while(true){
            try {
                connection=server.accept();

                inFromClient = new ObjectInputStream(connection.getInputStream());
                init=(InitialMessage)inFromClient.readObject();
                System.out.println(init.getUsername() + " just connected!");
                client = new User(init.getUsername(),connection,globalChat);
                clients.add(client);


                //New Thread for each User, responsible for sending and receiving messages
                //messages=new Thread(new MessagesHandler(client,clients));
                //messages.start();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Connection error:");
                System.out.println(e.getMessage());
            }
        }


    }

}
