import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainApp {

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
            Socket connection = new Socket("127.0.0.1",6789);
            writeToServer = new PrintWriter(connection.getOutputStream(),true);
            ServerListener listeningThread = new ServerListener(connection);
            pool.execute(listeningThread);

            while (true) {
                message = input.nextLine();
                if(message.equals("")){
                    break;
                }else{
                    writeToServer.println(message);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            writeToServer.close();
        }
    }
}
