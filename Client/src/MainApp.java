import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.*;

public class MainApp {

    //TODO Changing chatrooms

    //Variables
    private Scanner input = new Scanner(System.in);
    private String message,username;
    private ExecutorService pool = Executors.newSingleThreadExecutor();
    private ObjectOutputStream outToServer;
    private Socket connection;
    private ServerListener listeningThread;


    public static void main(String args[]){
        MainApp app = new MainApp();
        app.run();
    }

    /**
     * Main method responsible for connecting to server and sending messages.
     * Currently connected to localhost but later in development you will get to choose ip.
     */
    private void run(){
        try {
            //Connecting to server
            connection = new Socket("127.0.0.1",6789);

            //Setting up output stream to server
            outToServer = new ObjectOutputStream(connection.getOutputStream());

            //Create and run thread for listening for messages
            listeningThread = new ServerListener(connection);
            pool.execute(listeningThread);

            //Frame to get username from client
            username = JOptionPane.showInputDialog("Enter your username");

            System.out.println("Type a message:");

            //Main loop for inputs
            while (true) {
                //Input message and send it to server
                //If nothing is entered close program
                message = input.nextLine();
                if(message.equals("")){
                    break;
                }else{
                    Message msg = new Message(username,message);
                    outToServer.writeObject(msg);
                    outToServer.flush();
                }
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }//Cleanup
        finally {
            try {
                outToServer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
