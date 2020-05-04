import java.io.Serializable;

public class Request implements Serializable {
    private RequestType type;
    private String chatroomName=null;

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
     * @param id Id of chatroom you want to target with request.
     */
    public Request(RequestType type, String chatroomName){
        this.type=type;
        this.chatroomName=chatroomName;
    }


    /**
     * Returns type of Request.
     * @return the type of request.
     */
    //Getters
    public RequestType getType() {
        return type;
    }


    public String getChatroomName(){
        return chatroomName;
    }

    /**
     * Sets type of request.
     * @param type type of your request;
     */
    //Setters
    public void setType(RequestType type) {
        this.type = type;
    }

}
