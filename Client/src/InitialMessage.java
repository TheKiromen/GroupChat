import java.io.Serializable;

public class InitialMessage  implements Serializable {

    private String username;

    InitialMessage(String username){
        this.username=username;
    }

    public String getUsername(){
        return username;
    }

}
