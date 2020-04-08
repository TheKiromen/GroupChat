import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainApp {

    //TODO Make chatrooms, ability to change your room.

    //Variables
    private ConcurrentHashMap<Integer, ArrayList<ClientHandler>> chatrooms = new ConcurrentHashMap<Integer, ArrayList<ClientHandler>>();
    //private ArrayList<ClientHandler> users = new ArrayList<>();
    private ExecutorService pool = Executors.newFixedThreadPool(5);
    private ServerSocket server;
    private ClientHandler clientThread;

    public static void main(String[] args){
        MainApp app = new MainApp();
        app.run();
    }

    /**
     * Creates server socket, accepts incoming client connections until you shut down server.
     * Creates new Thread responsible for communication for every client and runs it.
     */
    private void run(){
        try {
            //Server setup
            server = new ServerSocket(6789);
            chatrooms.put(1,new ArrayList<ClientHandler>());

            //\/ Comment to disable infinite loop warning
            //noinspection InfiniteLoopStatement
            while(true){

                //Listen for new connection
                System.out.println("Waiting for connection...");
                Socket connection = server.accept();
                System.out.println("New client connected!");

                //Create and run new thread for each client
                clientThread = new ClientHandler(connection,chatrooms,1);
                chatrooms.get(1).add(clientThread);
                pool.execute(clientThread);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
