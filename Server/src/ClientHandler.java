import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {

    //Variables
    private Object input;
    private String username;
    private Chatroom currentChatroom;
    private ArrayList<Chatroom> chatrooms;
    private Socket client;
    private ObjectInputStream inFromClient;
    private ObjectOutputStream outToClient;
    private ServerFrame frame;


    /**
     * Creates new thread responsible for communication with specified client
     * @param clientSocket client Socket you want communicate with
     * @param users list of users in chatroom.
     * @throws IOException When cant establish stream connection with client.
     */
    //Constructor, setting up communication streams
    public ClientHandler(Socket clientSocket, ArrayList<Chatroom> users,Chatroom chatroom, ServerFrame f) throws IOException{
        this.client = clientSocket;
        this.chatrooms = users;
        this.currentChatroom =chatroom;
        this.frame=f;

        inFromClient = new ObjectInputStream(client.getInputStream());
        outToClient = new ObjectOutputStream(client.getOutputStream());
    }

    /**
     * Main method responsible for receiving and sending messages.
     * Sends received message to all users in chatroom.
     * Also responsible for handling user requests.
     */
    @Override
    public void run() {
        try{
            //Send message about new connection to chatroom
            Message msg=(Message)inFromClient.readObject();
            username=msg.getSender();
            frame.writeToConsole(username+" connected.");
            sendMessageToAll(new Message("[Server]",username+" joined!"));

            //Main loop for broadcasting messages
            while(true){
                //Listen for message and send it to all clients in your chatroom
                input=inFromClient.readObject();
                try {
                    //If incoming Object is a message, broadcast it.
                    msg=(Message)input;
                    frame.writeToConsole(msg.getSender()+" sent: \"" + msg.getContent()+"\" to chatroom: "+ currentChatroom.getChatroomName());
                    sendMessageToAll(msg);
                }catch(ClassCastException ex){

                    //If incoming Object is a request, handle it according to its type.
                    Request r = (Request)input;
                    frame.writeToConsole(username+" sent new request: "+r.getType());

                    //Chatroom change request
                    if(r.getType()==RequestType.CHATROOM_CHANGE){
                        //Check if chatroom already exists
                        for(int i=0;i<chatrooms.size();i++){
                            //If exists send change to it, send successful response
                            if(chatrooms.get(i).getChatroomName().equals(r.getChatroomName())){
                                changeChatroom(chatrooms.get(i));

                                //Send successful change response
                                outToClient.writeObject(new Request(RequestType.CHATROOM_CHANGE,r.getChatroomName(),true));
                                outToClient.flush();

                                break;
                            }//If does not exits send invalid chatroom response
                            else if(i==chatrooms.size()-1){
                                //Send invalid change response
                                outToClient.writeObject(new Request(RequestType.CHATROOM_CHANGE,r.getChatroomName(),false));
                                outToClient.flush();
                                break;
                            }
                        }
                    //Create new chatroom request
                    }else if(r.getType()==RequestType.CREATE_CHATROOM){
                        //Check if chatroom already exists
                        for(int i=0;i<chatrooms.size();i++){
                            //If exists send "invalid chatroom" response
                            if(chatrooms.get(i).getChatroomName().equals(r.getChatroomName())){
                                outToClient.writeObject(new Request(RequestType.CREATE_CHATROOM,r.getChatroomName(),false));
                                outToClient.flush();
                                break;

                            }//If does not exits create chatroom, and connect user to it.
                            else if(i==chatrooms.size()-1){
                                chatrooms.add(new Chatroom(r.getChatroomName(),new ArrayList<ClientHandler>()));
                                //created chatroom is added to end of arrayList
                                changeChatroom(chatrooms.get(chatrooms.size()-1));

                                //Send successful creation response
                                outToClient.writeObject(new Request(RequestType.CREATE_CHATROOM,r.getChatroomName(),true));
                                outToClient.flush();
                                break;
                            }
                        }
                    }


                }
            }
        }
        //Handle user disconnection
        catch(IOException e){
            frame.writeToConsole(username+" disconnected.");
            currentChatroom.getUsers().remove(this);
            try {
                sendMessageToAll(new Message("[Server]",username+" disconnected."));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        //Wrong object type
        catch(ClassNotFoundException e){
            System.err.println(e.getMessage());
        }
        //Cleanup
        finally{
            try {
                inFromClient.close();
                outToClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Safely sends message to this client.
     * @param msg Message you want to send.
     * @throws IOException When stream error occurs.
     */
    //Method for safe message sending from inside and outside of the Thread
    public void sendMessage(Message msg) throws IOException {
        outToClient.writeObject(msg);
        outToClient.flush();
    }

    /**
     * Safely sends message to all clients in chatroom.
     * @param msg Message you want to send.
     * @throws IOException When stream error occurs.
     */
    //Method for safe message broadcasting from inside and outside the Thread
    public void sendMessageToAll(Message msg) throws IOException{
        for(ClientHandler u : currentChatroom.getUsers()){
            u.sendMessage(msg);
        }
    }

    /**
     * Change chatroom user is connected to.
     * @param chatroom Chatroom you want to connect to
     * @throws IOException When stream error occurs.
     */
    //Method for changing chatroom
    private void changeChatroom(Chatroom chatroom) throws IOException {
        this.currentChatroom.getUsers().remove(this);
        sendMessageToAll(new Message("[Server]",username+" has left the room."));
        this.currentChatroom =chatroom;
        sendMessageToAll(new Message("[Server]",username+" has joined the room."));
        chatroom.getUsers().add(this);
    }

}
