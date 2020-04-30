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

public class ChatWindow extends JFrame {

    //Variables
    private ObjectOutputStream outToServer;
    private String username;
    private ExecutorService pool = Executors.newSingleThreadExecutor();
    private Socket connection;
    private ServerListener listeningThread;
    private MessageSender sender = new MessageSender();
    private Message msg;

    //GUI variables
    private JPanel mainPanel;
    private JTextField inputField;
    private MyButton sendButton;
    private JTextArea chatArea;
    private JLabel user;
    private ChatWindow frame;

    //Fonts
    private Font componentsFont = new Font("Arial", Font.BOLD, 16);
    private Font textAreaFont = new Font("Arial", Font.PLAIN, 14);

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


        //InputField setup
        inputField.setCaretColor(Color.BLACK);
        inputField.setFont(textAreaFont);
        inputField.addActionListener(sender);

        //User label
        user.setText("Connected as: "+username);
        user.setFont(componentsFont);
    }

    private void createUIComponents(){
        sendButton=new MyButton();
        sendButton.setContentAreaFilled(false);
    }

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

    //Write text to textArea
    public void writeToConsole(String text){chatArea.append(text+"\n"); }

    private class MessageSender implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            msg=new Message(username,inputField.getText());
            try{
                outToServer.writeObject(msg);
                outToServer.flush();
                inputField.setText("");
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
