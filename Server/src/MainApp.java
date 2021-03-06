import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Main class that implements server sockets.
 * A server socket waits for connections to come in over the network.
 */
public class MainApp implements Runnable{

    //Variables
    private ArrayList<Chatroom> chatrooms = new ArrayList<>();
    private ExecutorService pool = Executors.newCachedThreadPool();
    private ServerSocket server;
    private ClientHandler clientThread;
    private ServerFrame frame;

    /**
     * Handles server's main GUI.
     * @param s main server frame.
     */
    //Constructor
    MainApp(ServerFrame s){
        this.frame=s;
    }

    /**
     * Creates server socket, accepts incoming client connections until you shut down server.
     * Creates new Thread responsible for communication for every client and runs it.
     */
    public void run(){
        try {

            //Server setup
            server = new ServerSocket(6789);

            //Create default global chatroom
            chatrooms.add(new Chatroom("Global",new ArrayList<ClientHandler>()));


            frame.writeToConsole("Waiting for connection...");

            //\/ Comment for Intellij to disable infinite loop warning
            //noinspection InfiniteLoopStatement
            while(true){

                //Listen for new connection
                Socket connection = server.accept();
                //frame.writeToConsole("New client connected!");

                //Create and run new thread for each client
                clientThread = new ClientHandler(connection,chatrooms, chatrooms.get(0),frame);
                chatrooms.get(0).getUsers().add(clientThread);
                pool.execute(clientThread);
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
