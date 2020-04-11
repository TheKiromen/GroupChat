import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainApp {

    //TODO Make chatrooms, ability to change your room.

    //Variables
    private ConcurrentHashMap<Integer, ArrayList<ClientHandler>> chatrooms = new ConcurrentHashMap<Integer, ArrayList<ClientHandler>>();
    private ExecutorService pool = Executors.newFixedThreadPool(10);
    private ServerSocket server;
    private ClientHandler clientThread;

    private ServerFrame frame;

    public static void main(String[] args){
        MainApp app = new MainApp();
        app.run();
    }


    /**
     * Creates server socket, accepts incoming client connections until you shut down server.
     * Creates new Thread responsible for communication for every client and runs it.
     */
    private void run(){
        frame = new ServerFrame();
        frame.mainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(frame.isRunning){
                    frame.mainButton.setText("Start Server");
                    frame.status.setText("Offline");
                    frame.status.setForeground(Color.RED);
                    frame.isRunning=false;
                }else{
                    frame.mainButton.setText("Stop Server");
                    frame.status.setText("Online");
                    frame.status.setForeground(Color.GREEN);
                    frame.isRunning=true;
                }
            }
        });
        try {

            //Server setup
            server = new ServerSocket(6789);

            //Create global chatroom
            chatrooms.put(1,new ArrayList<ClientHandler>());
            //TMP
            chatrooms.put(2,new ArrayList<ClientHandler>());

            frame.writeToConsole("Waiting for connection...");

            //\/ Comment for Intellij to disable infinite loop warning
            //noinspection InfiniteLoopStatement
            while(true){

                //Listen for new connection
                Socket connection = server.accept();
                frame.writeToConsole("New client connected!");

                //Create and run new thread for each client
                clientThread = new ClientHandler(connection,chatrooms,1,frame);
                chatrooms.get(1).add(clientThread);
                pool.execute(clientThread);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
