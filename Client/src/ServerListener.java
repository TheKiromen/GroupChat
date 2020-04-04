import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerListener implements Runnable {

    private Socket server;
    private BufferedReader readFromServer;

    ServerListener(Socket sv) throws IOException {
        this.server=sv;
        readFromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
    }

    @Override
    public void run() {
        try {
            while(true){
                String message = readFromServer.readLine();
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                readFromServer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
