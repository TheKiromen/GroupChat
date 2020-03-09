import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Set;

public class MessageSender implements Runnable {

    private Set<Chatroom> chatrooms;

    public MessageSender(Set<Chatroom> chatrooms){
        this.chatrooms=chatrooms;
    }

    public void run() {
        //Send messages
    }

}
