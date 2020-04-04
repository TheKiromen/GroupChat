import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainApp {

    //TODO Changing chatrooms

    //Variables
    private Scanner input = new Scanner(System.in);
    String message,username;
    private ExecutorService pool = Executors.newSingleThreadExecutor();
    private ObjectOutputStream outToServer;


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
            Socket connection = new Socket("127.0.0.1",6789);

            //Setting up output stream to server
            outToServer = new ObjectOutputStream(connection.getOutputStream());

            //Create and run thread for listening for messages
            ServerListener listeningThread = new ServerListener(connection);
            pool.execute(listeningThread);

            //Get username for client
            System.out.println("Enter your username:");
            username = input.nextLine();

            //Main loop for inputs
            while (true) {
                System.out.println("Enter your message:");
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
