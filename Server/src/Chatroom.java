import java.util.Set;

public class Chatroom {

    private String name;
    private int ID;
    private Set<User> users;

    public Chatroom(String name, int ID){
        this.name=name;
        this.ID=ID;
    }
}
