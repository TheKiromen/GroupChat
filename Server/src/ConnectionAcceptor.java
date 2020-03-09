import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionAcceptor implements Runnable{

    private ServerSocket server;
    private Chatroom globalChat;
    Socket connection;

    public ConnectionAcceptor(ServerSocket serverSocket,Chatroom chatroom){
        this.server=serverSocket;
        this.globalChat=chatroom;
    }

    public void run(){
        while(true){
            try {
                connection=server.accept();
                //TEMP - get nickname from users message object
                //Use objectInputStream to get initial message from client;
                globalChat.addUser(connection);
            } catch (IOException e) {
                System.out.println("Connection error:");
                System.out.println(e.getMessage());
            }
        }
    }

}
