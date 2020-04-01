import java.awt.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.io.*;

public class MainApp {

    private Socket connection;
    private ObjectOutputStream outToSever;
    private String userName;
    private String str = null;
    private BufferedReader userInput;

    public static void main(String args[]){
        MainApp app = new MainApp();
        app.run();
    }

    private void run(){
        try {
            connection = new Socket("127.0.0.1",6789);

            //Send initial message to server
            //User input - username setting
            outToSever = new ObjectOutputStream(connection.getOutputStream());

            Scanner myObj = new Scanner(System.in);
            System.out.println("Enter your username:");
            userName = myObj.nextLine();
            outToSever.writeObject(userName);

            //Type a message, send it to server
            userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Type a message: ");

            while ((str = userInput.readLine()) != null)
            {
                System.out.println("[CLIENT]"+ userName +": " + str);
                outToSever.writeObject(str);
                outToSever.flush();
            }

            //Start a thread for listening to messages from server
            Thread t = new Thread(new MessageReceiver(connection));
            t.start();

        } catch (IOException e) {
            System.out.println("Error while connecting to server:");
            System.out.println(e.getMessage());
        }

    }
}
