import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

/**
 * Class responsible for handling incoming messages and connecting to server.
 */
public class ServerListener implements Runnable {

    //Variables
    private Socket server;
    private ObjectInputStream inFromServer;
    private ChatWindow frame;
    private Object input;

    /**
     * Creates new thread with associated socket. Responsible for receiving messages from server.
     * @param sv socket connected to server.
     * @throws IOException When cant establish connection stream with server.
     */
    //Constructor, setting up input stream from server
    ServerListener(Socket sv, ChatWindow f) throws IOException {
        this.server=sv;
        this.frame=f;
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
                try {
                    //Read and print out message
                    input=inFromServer.readObject();
                    Message msg = (Message) input;
                    frame.writeToConsole(msg.getSender() + ": " + msg.getContent());
                //If input is a response to your request.
                }catch(ClassCastException e){
                    try {
                        Request r = (Request) input;
                        //Create chatroom request
                        if (r.getType() == RequestType.CREATE_CHATROOM) {
                            //If successful - change label
                            if (r.getResponse() == true) {
                                frame.changeChatroomLabel(r.getChatroomName());
                                frame.writeToConsole("Changed chatroom to " + r.getChatroomName());
                            }//If failed - show dialog
                            else {
                                JOptionPane.showMessageDialog(frame, "Chatroom already exists.");
                            }
                            //Change chatroom request
                        } else if (r.getType() == RequestType.CHATROOM_CHANGE) {
                            //If successful - change label
                            if (r.getResponse() == true) {
                                frame.writeToConsole("Changed chatroom to " + r.getChatroomName());
                                frame.changeChatroomLabel(r.getChatroomName());
                            }//If failed - show dialog
                            else {
                                JOptionPane.showMessageDialog(frame, "You are already connected to this chatroom.");
                            }
                        }
                    }catch(ClassCastException ex){
                        String[] tmp = (String[])input;
                        frame.showChangeChatroomDialog(tmp);
                    }
                }
            }

        }//Exception when server closes
        catch(SocketException e){
            JOptionPane.showMessageDialog(null,"Lost connection to the server");
            System.exit(0);
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }//Cleanup
        finally {
            //Close communication stream if its not already closed
            try {
                inFromServer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
