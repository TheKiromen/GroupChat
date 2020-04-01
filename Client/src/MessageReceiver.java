import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class MessageReceiver implements Runnable{

    private final Socket connection;
    private ObjectInputStream inFromServer=null;
    private BufferedReader in;
    private PrintWriter out;


    public MessageReceiver(Socket connection){
        this.connection=connection;
        try {
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            out = new PrintWriter(connection.getOutputStream());
            inFromServer = new ObjectInputStream(connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Listens for message from server and prints it
    public void run() {
        while(true){
            try {
                //TODO Improve on this so it makes more sense.
                // Nothing happens here
                System.out.println("Out:" + out);
                System.out.println(in.readLine());
                System.out.println(inFromServer.readObject());

            }//Ends Thread if server closes
            catch(SocketException e){
                System.out.println("Connection to server lost.");
                break;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
