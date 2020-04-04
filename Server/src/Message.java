import java.io.Serializable;

public class Message implements Serializable {

    private String content;

    Message(String content){
        this.content=content;
    }

    public String getContent(){
        return content;
    }


}
