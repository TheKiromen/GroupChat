import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainApp {

    private Socket connection;
    private ObjectOutputStream outToSever;
    private String username;

    public static void main(String args[]){
        MainApp app = new MainApp();
        app.run();
    }

    private void run(){
        try {
            connection = new Socket("127.0.0.1",6789);

            //Send initial message to server
            outToSever = new ObjectOutputStream(connection.getOutputStream());
            username = JOptionPane.showInputDialog("Enter your username");
            outToSever.writeObject(new InitialMessage(username));
            outToSever.flush();

            //Start a thread for listening to messages from server
            //Thread t = new Thread(new MessageReceiver(connection));
            //t.start();

        } catch (IOException e) {
            System.out.println("Error while connecting to server:");
            System.out.println(e.getMessage());
        }

    }
}
