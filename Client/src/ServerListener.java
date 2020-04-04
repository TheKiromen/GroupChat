import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;

public class ServerListener implements Runnable {

    //Variables
    private Socket server;

    private ObjectInputStream inFromServer;

    //Constructor, setting up input stream from server
    ServerListener(Socket sv) throws IOException {
        this.server=sv;
        inFromServer = new ObjectInputStream(sv.getInputStream());
    }

    @Override
    public void run() {
        try {
            //Main loop for listening for messages
            while(true){
                //Read and print out message
                Message msg = (Message)inFromServer.readObject();
                System.out.println(msg.getSender()+": "+msg.getContent());
            }

        }//Exception when server closes
        catch(SocketException e){
            System.err.println("Disconnected from the server.");
        }
        catch (IOException e) {
            e.printStackTrace();
        }//Cleanup
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                inFromServer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
