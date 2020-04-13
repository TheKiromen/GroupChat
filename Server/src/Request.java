import java.io.Serializable;

public class Request implements Serializable {
    private RequestType type;
    private Integer id = null;

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
    public Request(RequestType type, Integer id){
        this.type=type;
        this.id = id;
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
     * Returns ID of chatroom targeted by request.
     * @return the id of chatroom targeted by request.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets type of request.
     * @param type type of your request;
     */
    //Setters
    public void setType(RequestType type) {
        this.type = type;
    }

    /**
     * Sets destined chatroom
     * @param id id of your targeted chatroom.
     */
    public void setId(Integer id) {
        this.id = id;
    }
}
