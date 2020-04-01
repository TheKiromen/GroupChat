import java.net.Socket;

public class User {

    private String nickname;
    private Socket socket;
    private Chatroom chatroom;

    public User(String nickname, Socket socket,Chatroom chatroom){
        this.nickname=nickname;
        this.socket=socket;
        this.chatroom=chatroom;
    }

    //Getters
    public String getNickname(){
        return this.nickname;
    }
    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }
    public Socket getSocket(){
        return socket;
    }
    public Chatroom getChatroom(){
        return chatroom;
    }
}
