import java.util.ArrayList;

/**
 * Class responsible for chatroom handling.
 * Gives information about chatrooms created with lists of users.
 */
public class Chatroom {

    private String chatroomName;
    private ArrayList<ClientHandler> users;
    private boolean isPublic=true;
    private String password;

    /**
     * Creates Chatroom object with specified chatroom name and list of users.
     * @param chatroomName name of the chatroom.
     * @param users clients in this chatroom.
     */
    public Chatroom(String chatroomName, ArrayList<ClientHandler> users){
        this.chatroomName=chatroomName;
        this.users=users;
    }

    /**
     * In the future, this constructor will allow to handle private chatroom with specified name and password required.
     * @param chatroomName name of the chatroom.
     * @param password unique password for the chatroom.
     * @param users clients in this chatroom.
     */
    public Chatroom(String chatroomName,String password, ArrayList<ClientHandler> users){
        this.chatroomName=chatroomName;
        this.users=users;
        this.password=password;
        this.isPublic=false;
    }

    /**
     * Gives access to the chatroom name outside this class.
     * Provides possibility to create or change a chatroom and send a message.
     * @return name of the chatroom.
     */
    public String getChatroomName(){
        return chatroomName;
    }

    /**
     * Gives access to the list of users outside this class.
     * @return list of users.
     */
    public ArrayList<ClientHandler> getUsers(){
        return users;
    }

    /**
     * In the future, it will be responsible for checking if the chatroom is public.
     * @return information if the chatroom is public.
     */
    public boolean isPublic(){
        return isPublic;
    }

    /**
     * In the future, it will give access to the password outside this class.
     * @return password to the chatroom.
     */
    public String getPassword(){
        return password;
    }
}
