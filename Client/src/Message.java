import java.io.Serializable;

public class Message implements Serializable {

    private String sender;
    private String content;

    Message(String sender, String content){
        this.sender=sender;
        this.content=content;
    }

    public String getSender(){
        return sender;
    }

    public String getContent(){
        return content;
    }
}
