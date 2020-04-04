import com.sun.security.ntlm.Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainApp {

    private ArrayList<ClientHandler> users = new ArrayList<>();
    private ExecutorService pool = Executors.newFixedThreadPool(5);

    public static void main(String[] args){
        MainApp app = new MainApp();
        app.run();
    }

    private void run(){
        try {
            ServerSocket server = new ServerSocket(6789);
            while(true){
                System.out.println("Waiting for connection");
                Socket connection = server.accept();
                System.out.println("Client connected");
                ClientHandler clientThread = new ClientHandler(connection);
                users.add(clientThread);
                pool.execute(clientThread);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
