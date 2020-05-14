import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Client side of simple chatroom chat application.
 *
 * @autor Monika Haracz, Dominik Kruczek
 */

public class ChatWindow extends JFrame {

    //Component variables
    private JPanel mainPanel;
    private JTextField inputField;
    private MyButton sendButton;
    private JTextArea chatArea;
    private JLabel user;
    private JLabel chatroom;
    private MyButton changeRoom;
    private MyButton newRoom;
    private ChatWindow frame;

    //Fonts
    private Font componentsFont = new Font("Arial", Font.BOLD, 16);
    private Font statusFont = new Font("Arial",Font.PLAIN,12);
    private Font textAreaFont = new Font("Arial", Font.PLAIN, 14);

    //Program Variables
    private ObjectOutputStream outToServer;
    private String username;
    private ExecutorService pool = Executors.newSingleThreadExecutor();
    private Socket connection;
    private ServerListener listeningThread;
    private MessageSender sender = new MessageSender();
    private Message msg;



    //Constructor
    public ChatWindow(String user) {

        username=user;
        frame=this;

        setUpConnection();

        //Components setup
        setUpComponents();
        add(mainPanel);
        setSize(600, 450);
        setMinimumSize(new Dimension(600, 450));

        //FrameSetup
        setTitle("Client Application");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);

    }


    /**
     * Configures components before adding them to frame.
     */
    private void setUpComponents()
    {
       //ChatArea setup
        chatArea.setFont(textAreaFont);
        chatArea.setForeground(Color.getHSBColor(35, 0.01f, 0.85f));
        chatArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        chatArea.setCaretColor(Color.WHITE);
        chatArea.setEditable(false);

        //Button
        sendButton.setForeground(Color.getHSBColor(35, 0.01f, 0.85f));
        sendButton.setFont(componentsFont);
        sendButton.setFocusable(false);
        sendButton.addActionListener(sender);



        //Create new chatroom button
        newRoom.setFont(statusFont);
        newRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new CreateChatroom(outToServer);
            }
        });

        //Change current chatroom button
        changeRoom.setFont(statusFont);
        changeRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    outToServer.writeObject(new Request(RequestType.GET_CHATROOM_LIST));
                    outToServer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        //InputField setup
        inputField.setCaretColor(Color.BLACK);
        inputField.setFont(textAreaFont);
        inputField.addActionListener(sender);

        //Status labels
        user.setText("Connected as: "+username);
        user.setFont(statusFont);

        //chatroom.setText("Chatroom ID: "+chatroomID);
        chatroom.setFont(statusFont);
    }

    /**
     * Initializes custom components
     */
    private void createUIComponents(){
        sendButton=new MyButton();
        sendButton.setContentAreaFilled(false);
        sendButton.setFocusable(false);

        changeRoom=new MyButton();
        changeRoom.setContentAreaFilled(false);
        changeRoom.setFocusable(false);

        newRoom=new MyButton();
        newRoom.setContentAreaFilled(false);
        newRoom.setFocusable(false);
    }

    /**
     * Sets up connection with the server.
     */
    private void setUpConnection(){
        try {
            //Connecting to server
            connection = new Socket("127.0.0.1",6789);

            //Setting up output stream to server
            outToServer = new ObjectOutputStream(connection.getOutputStream());

            //Create and run thread for listening for messages
            listeningThread = new ServerListener(connection,frame);
            pool.execute(listeningThread);

            //Send your nickname to server
            outToServer.writeObject(new Message(username,""));
            outToServer.flush();

        }
        //Handle if server is offline
        catch(ConnectException e){
            JOptionPane.showMessageDialog(null,"Server is offline.");
            System.exit(0);
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    //Write text to chatArea
    public void writeToConsole(String text){chatArea.append(text+"\n"); }


    //Change current chatroom label
    public void changeChatroomLabel(String text){
        chatroom.setText("Chatroom: "+text);
        chatroom.repaint();
    }


    public void showChangeChatroomDialog(String[] chatrooms){
        new ChangeChatroom(chatrooms,outToServer);
    }


    //Internal class that defines button behavior
    private class MessageSender implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            //Create new message
            msg=new Message(username,inputField.getText());
            try{
                //Send message to server and clear input field
                outToServer.writeObject(msg);
                outToServer.flush();
                inputField.setText("");
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
