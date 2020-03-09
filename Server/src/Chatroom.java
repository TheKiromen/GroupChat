import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Chatroom {

    private String name;
    private int ID;
    //private Set<User> users = new HashSet<Users>();
    private Set<Socket> users = new HashSet<Socket>();

    public Chatroom(String name, int ID){
        this.name=name;
        this.ID=ID;
    }

    //TMP
    public boolean addUser(Socket sc){
        this.users.add(sc);
        return true;
    }

    /*
    public boolean addUser(User usr){
        this.users.add(usr);
        return true;
    }
     */
}
