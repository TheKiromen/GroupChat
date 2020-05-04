import java.util.ArrayList;

public class Chatroom {

    private String chatroomName;
    private ArrayList<ClientHandler> users;
    private boolean isPublic=true;
    private String password;

    public Chatroom(String chatroomName, ArrayList<ClientHandler> users){
        this.chatroomName=chatroomName;
        this.users=users;
    }

    public Chatroom(String chatroomName,String password, ArrayList<ClientHandler> users){
        this.chatroomName=chatroomName;
        this.users=users;
        this.password=password;
        this.isPublic=false;
    }

    public String getChatroomName(){
        return chatroomName;
    }

    public ArrayList<ClientHandler> getUsers(){
        return users;
    }

    public boolean isPublic(){
        return isPublic;
    }

    public String getPassword(){
        return password;
    }
}
