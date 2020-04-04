import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Date;


public class MainApp {

    private ServerSocket server;
    private Chatroom global = new Chatroom("Global",1);
    private ArrayList<User> clients = new ArrayList<User>();
    private String date;

    public static void main(String[] args){
        MainApp app = new MainApp();
        app.run();
    }

    private void run(){
        try {
            server = new ServerSocket(6789);
            date = (new Date()).toString();
            System.out.println("Server's sending date: " + date);
            System.out.println("Server is waiting for client connection...");

        } catch (IOException e) {
            System.out.println("Error while trying to create server:");
            System.out.println(e.getMessage());
        }

        //New Thread to handle connections
        Thread connections = new Thread(new ConnectionAcceptor(server,global,clients));
        connections.start();

    }
}
