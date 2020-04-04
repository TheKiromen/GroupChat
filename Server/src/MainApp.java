import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainApp {

    //Variables
    private ArrayList<ClientHandler> users = new ArrayList<>();
    private ExecutorService pool = Executors.newFixedThreadPool(5);

    public static void main(String[] args){
        MainApp app = new MainApp();
        app.run();
    }

    private void run(){
        try {
            //Server setup
            ServerSocket server = new ServerSocket(6789);
            while(true){

                //Listen for new connection
                System.out.println("Waiting for connection");
                Socket connection = server.accept();
                System.out.println("Client connected");

                //Create and run new thread for each client
                ClientHandler clientThread = new ClientHandler(connection,users);
                users.add(clientThread);
                pool.execute(clientThread);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
