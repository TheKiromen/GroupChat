import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MessagesHandler implements Runnable{

    private final User client;
    private final ArrayList<User> clients;
    private ObjectInputStream inFromClient;
    private ObjectOutputStream outToClient;


    public MessagesHandler(User client,ArrayList<User> clients){
        this.client=client;
        this.clients=clients;
        try {
            this.inFromClient= new ObjectInputStream(client.getSocket().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        //TODO make server not shit itself when user disconnects
        //TODO listen for message and send it over to everyone else in the same chatroom
        //TODO move listening for initial message to ConnectionAcceptor
        while(true){
            try {
                //Listen for initial message (take this out of the loop)
                System.out.println(inFromClient.readObject());

                //Send message to client (make it send message to all clients)
                outToClient = new ObjectOutputStream(client.getSocket().getOutputStream());
                outToClient.writeObject(new Dimension(22, 22));
                outToClient.flush();
                for(int i=0;i<50;i++){
                    outToClient.writeObject(new Dimension(i,i));
                    outToClient.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
