import java.io.IOException;
import java.net.Socket;

public class MainApp {

    private Socket client;

    public static void main(String args[]){
        MainApp app = new MainApp();
        app.run();
    }

    private void run(){
        try {
            client = new Socket("127.0.0.1",6789);
        } catch (IOException e) {
            System.out.println("Error while connecting bo server:");
            System.out.println(e.getMessage());
        }
        //TMP to not disconnect from server
        while(true){}
    }
}
