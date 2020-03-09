import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ConnectionAcceptor implements Runnable{

    private final ServerSocket server;
    private final Chatroom globalChat;
    private final ArrayList<User> clients;

    //TMP variables
    private Socket connection;
    private User client;
    private Thread messages;

    public ConnectionAcceptor(ServerSocket serverSocket, Chatroom chatroom, ArrayList<User> clients){
        this.server=serverSocket;
        this.globalChat=chatroom;
        this.clients=clients;
    }

    public void run(){
        while(true){
            try {
                connection=server.accept();
                //TEMP - get nickname from users message object
                //Use objectInputStream to get initial message from client;
                client = new User("Jan",connection,globalChat);
                clients.add(client);

                messages=new Thread(new MessagesHandler(client,clients));
                messages.run();

            } catch (IOException e) {
                System.out.println("Connection error:");
                System.out.println(e.getMessage());
            }
        }
    }

}
