import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;

public class MainApp {

    private ServerSocket server;
    private Set<Chatroom> chatrooms;

    public static void main(String[] args){
        MainApp app = new MainApp();
        app.run();
    }

    private void run(){
        try {
            server = new ServerSocket(6789);
        } catch (IOException e) {
            System.out.println("Error while trying to create server");
            System.out.println(e.getMessage());
        }

        Thread connections = new Thread(new ConnectionAcceptor());
        Thread messages = new Thread(new MessageSender());
    }
}
