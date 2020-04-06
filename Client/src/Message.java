import java.io.Serializable;

public class Message implements Serializable {

    private String sender;
    private String content;

    /**
     * Creates Message object with specified sender and message content.
     * @param sender client who sent message.
     * @param content content of message itself.
     */
    Message(String sender, String content){
        this.sender=sender;
        this.content=content;
    }

    /**
     * Returns sender of the message.
     * @return the sender of this message.
     */
    public String getSender(){
        return sender;
    }

    /**
     * Returns body of the message.
     * @return the content of this message.
     */
    public String getContent(){
        return content;
    }
}
