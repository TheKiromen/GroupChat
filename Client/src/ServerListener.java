import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;

public class ServerListener implements Runnable {

    //Variables
    private Socket server;
    private ObjectInputStream inFromServer;

    /**
     * Creates new thread with associated socket. Responsible for receiving messages from server.
     * @param sv socket connected to server.
     * @throws IOException When cant establish connection stream with server.
     */
    //Constructor, setting up input stream from server
    ServerListener(Socket sv) throws IOException {
        this.server=sv;
        inFromServer = new ObjectInputStream(server.getInputStream());
    }

    /**
     * Main method responsible for managing incoming messages.
     * Prints out in console who sent message and it contents.
     */
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
            JOptionPane.showMessageDialog(null,"Interrupted connection");
            System.exit(0);
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
