import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;

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
                }catch(ClassCastException e){
                    Request r = (Request) input;
                    if(r.getType()==RequestType.CREATE_CHATROOM){
                        if(r.getResponse()==true){
                            frame.changeChatroomLabel(r.getChatroomName());
                            frame.writeToConsole("Changed chatroom to "+r.getChatroomName());
                        }else{
                            JOptionPane.showMessageDialog(frame,"Chatroom already exists.");
                        }
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
