import java.io.Serializable;

/**
 * Class responsible for handling clients' requests such as changing or creating new chatroom.
 */
public class Request implements Serializable {
    private RequestType type;
    private String chatroomName=null;
    private boolean response = false;

    /**
     * Create Request object of given type.
     * @param type Type of request you want to create.
     */
    public Request(RequestType type){
        this.type=type;
    }

    /**
     * Create Request of given type with destined chatroom.
     * @param type Type of request you want to create.
     * @param chatroomName Name of chatroom you want connect to.
     */
    public Request(RequestType type, String chatroomName){
        this.type=type;
        this.chatroomName=chatroomName;
    }

    /**
     * Create response Request regarding given requestType.
     * @param type type of request you want respond to.
     * @param chatroomName chatroom regarding this response.
     * @param response type of response, failed/succeeded operation.
     */
    public Request(RequestType type, String chatroomName, boolean response){
        this.type=type;
        this.chatroomName=chatroomName;
        this.response=response;
    }

    /**
     * Returns type of Request.
     * @return the type of request.
     */
    //Getters
    public RequestType getType() {
        return type;
    }

    /**
     * Returns type of Request.
     * @return name of the target chatroom.
     */
    public String getChatroomName(){
        return chatroomName;
    }

    /**
     * Returns response type
     * @return type of your response.
     */
    public boolean getResponse(){return response;}

    /**
     * Sets type of request.
     * @param type type of your request.
     */
    //Setters
    public void setType(RequestType type) {
        this.type = type;
    }

    /**
     * Sets type of request.
     * @param chatroomName Name of chatroom you want to connect.
     */
    public void setChatroomName(String chatroomName){
        this.chatroomName=chatroomName;
    }
}
