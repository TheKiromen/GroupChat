import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainApp {

    //TODO Send objets instead of strings, username selection support

    //Variables
    private Scanner input = new Scanner(System.in);
    private PrintWriter writeToServer;
    String message;
    private ExecutorService pool = Executors.newSingleThreadExecutor();


    public static void main(String args[]){
        MainApp app = new MainApp();
        app.run();
    }


    private void run(){
        try {
            //Connecting to server
            Socket connection = new Socket("127.0.0.1",6789);
            //Setting up output stream to server
            writeToServer = new PrintWriter(connection.getOutputStream(),true);
            //Create and run thread for listening for messages
            ServerListener listeningThread = new ServerListener(connection);
            pool.execute(listeningThread);

            //Main loop for inputs
            while (true) {
                //Input message and send it to server
                //If nothing is entered program closes
                message = input.nextLine();
                if(message.equals("")){
                    break;
                }else{
                    writeToServer.println(message);
                }
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }//Cleanup
        finally {
            writeToServer.close();
        }
    }
}
