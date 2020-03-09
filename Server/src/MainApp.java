import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MainApp {

    private ServerSocket server;
    private Set<Chatroom> chatrooms = new HashSet<Chatroom>();
    private Chatroom global = new Chatroom("Global",1);

    public static void main(String[] args){
        MainApp app = new MainApp();
        app.run();
    }

    private void run(){
        chatrooms.add(global);

        try {
            server = new ServerSocket(6789);
        } catch (IOException e) {
            System.out.println("Error while trying to create server:");
            System.out.println(e.getMessage());
        }

        Thread connections = new Thread(new ConnectionAcceptor(server,global));
        connections.run();

        Thread messages = new Thread(new MessageSender(chatrooms));
        messages.run();
    }
}
