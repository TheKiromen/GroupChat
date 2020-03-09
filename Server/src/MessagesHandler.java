import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MessagesHandler implements Runnable{

    private final User client;
    private final ArrayList<User> clients;


    public MessagesHandler(User client,ArrayList<User> clients){
        this.client=client;
        this.clients=clients;
    }

    public void run() {
        //TODO Make input and output object streams
        //TODO listen for message and send it over to everyone else in the same chatroom
    }

}
