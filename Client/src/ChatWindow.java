import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatWindow extends JFrame {
    private JPanel mainPanel;
    private JTextField inputField;
    private MyButton sendButton;
    private JTextArea chatArea;

    private ChatWindow window;

   //Fonts
    private Font componentsFont = new Font("Arial", Font.BOLD, 16);
    private Font textAreaFont = new Font("Arial", Font.PLAIN, 14);

    public ChatWindow() {

        window=this;

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

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                window.writeToConsole("test");
            }
        });


        //InputField setup
        inputField.setCaretColor(Color.BLACK);
        inputField.setFont(textAreaFont);

    }

    private void createUIComponents(){
        sendButton=new MyButton();
        sendButton.setContentAreaFilled(false);
    }

    //Write text to textArea
    public void writeToConsole(String text){chatArea.append(text+"\n"); }
}
