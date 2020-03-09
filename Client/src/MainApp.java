import java.io.IOException;
import java.net.Socket;

public class MainApp {
    public static void main(String args[]){
        MainApp app = new MainApp();
        app.run();
    }

    private void run(){
        try {
            Socket client = new Socket("127.0.0.1",6789);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
