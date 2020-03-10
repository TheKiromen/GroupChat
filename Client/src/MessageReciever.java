import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class MessageReciever implements Runnable{

    private final Socket connection;
    private ObjectInputStream inFromServer=null;
    //TODO remove later
    int i=0;

    public MessageReciever(Socket connection){
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
                i++;
                System.out.println("Message number"+i+" "+inFromServer.readObject());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
