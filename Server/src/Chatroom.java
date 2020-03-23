public class Chatroom {

    private String name;
    private int ID;

    public Chatroom(String name, int ID){
        this.name=name;
        this.ID=ID;
    }

    public boolean equals(Chatroom c){
        if(this.name==c.name&&this.ID==c.ID){
            return true;
        }else{
            return false;
        }
    }

}
