import javax.swing.*;
import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainApp implements Runnable{

    //Variables
    private Scanner input = new Scanner(System.in);
    private String message,username;
    private ExecutorService pool = Executors.newSingleThreadExecutor();
    private ObjectOutputStream outToServer;
    private Socket connection;
    private ServerListener listeningThread;


    public MainApp(String username){
        this.username=username;
    }


    /**
     * Main method responsible for connecting to server and sending messages.
     * Currently connected to localhost but later in development you will get to choose ip.
     */
    public void run(){
        try {
            //Connecting to server
            connection = new Socket("127.0.0.1",6789);

            //Setting up output stream to server
            outToServer = new ObjectOutputStream(connection.getOutputStream());

            //Create and run thread for listening for messages
            listeningThread = new ServerListener(connection);
            pool.execute(listeningThread);

            //Send your nickname to server
            outToServer.writeObject(new Message(username,""));
            outToServer.flush();

            System.out.println("Type a message:");

            //Main loop for inputs
            while (true) {
                //Input message and send it to server
                //If nothing is entered close program
                message = input.nextLine();
                if(message.equals("")){
                    break;
                }else if(message.equals("switch2")){
                    outToServer.writeObject(new Request(RequestType.CHATROOM_CHANGE,2));
                    outToServer.flush();
                }else{
                    Message msg = new Message(username,message);
                    outToServer.writeObject(msg);
                    outToServer.flush();
                }
            }

        }
        //Handle if server is offline
        catch(ConnectException e){
            JOptionPane.showMessageDialog(null,"Server is offline.");
            System.exit(0);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        //Cleanup
        finally {
            try {
                if(outToServer!=null){
                    outToServer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
