import java.awt.*;
import java.io.*;
import java.net.SocketException;
import java.util.ArrayList;



public class MessagesHandler implements Runnable{

    private final User client;
    private final ArrayList<User> clients;
    private ObjectInputStream inFromClient;
    private ObjectOutputStream outToClient;
    private String str = null;
    private String nickName = null;
    private PrintWriter out;



    public MessagesHandler(User client,ArrayList<User> clients){
        this.client=client;
        this.clients=clients;
        try {
            this.inFromClient= new ObjectInputStream(client.getSocket().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void run(){
        //TODO move listening for initial message to ConnectionAcceptor

        while(true){
            try {
                //Listen for initial message (take this out of the loop)
                  nickName = inFromClient.readObject().toString();
                  client.setNickname(nickName);
                  System.out.println("Welcome " + client.getNickname());

                  //Gets message from client and prints it
                while ((str = inFromClient.readObject().toString()) != null)
                {
                    System.out.println("[SERVER] "+ client.getNickname() + ": " + str);

                    //TODO make it send message to all clients
                    out = new PrintWriter(client.getSocket().getOutputStream());
                    //in = new BufferedReader(new InputStreamReader(client.getSocket().getInputStream()));

                    outToClient = new ObjectOutputStream(client.getSocket().getOutputStream());
                    outToClient.writeObject(new String("[FROMServer] ") + nickName + " " + str);
                    outToClient.flush();
                }
                /*  ---Old version: checking if messages are sent properly---

                 outToClient.writeObject(new String("New attempt of real time sending messages"));
                    outToClient.flush();

                for(int i=0;i<10;i++){
                    outToClient.writeObject(new String("Attempt no: ")+(i+1));
                    outToClient.flush();
                }
                */


            }//Deletes Thread when user disconnects
            catch(SocketException e){
                clients.remove(client);
                break;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
