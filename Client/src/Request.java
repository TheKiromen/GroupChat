import java.io.Serializable;

public class Request implements Serializable {
    private RequestType type;
    private Integer id = null;

    public Request(RequestType type){
        this.type=type;
    }

    public Request(RequestType type, Integer id){
        this.type=type;
        this.id = id;
    }

    //Getters
    public RequestType getType() {
        return type;
    }

    public Integer getId() {
        return id;
    }

    //Setters
    public void setType(RequestType type) {
        this.type = type;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
