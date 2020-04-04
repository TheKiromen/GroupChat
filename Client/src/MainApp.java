import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MainApp {

    private Scanner input = new Scanner(System.in);
    private PrintWriter writeToServer;
    private BufferedReader readFromServer;
    String message;

    public static void main(String args[]){
        MainApp app = new MainApp();
        app.run();
    }

    private void run(){
        try {
            Socket connection = new Socket("127.0.0.1",6789);
            writeToServer = new PrintWriter(connection.getOutputStream(),true);
            readFromServer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while (true) {

                message = input.nextLine();
                if(message.equals("")){
                    break;
                }else{
                    writeToServer.println(message);
                    System.out.println(readFromServer.readLine());
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
