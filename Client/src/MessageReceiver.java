import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;

public class MessageReceiver implements Runnable{

    private final Socket connection;
    private ObjectInputStream inFromServer=null;
    //TODO remove later
    int i=0;

    public MessageReceiver(Socket connection){
        this.connection=connection;
        try {
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
                System.out.println("Message number "+i+" "+inFromServer.readObject());
                i++;
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
