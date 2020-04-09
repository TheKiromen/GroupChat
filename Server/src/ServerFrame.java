
import javax.swing.*;
import java.awt.*;

public class ServerFrame extends JFrame {

    //\/ These are managed by Intellij's GUI designer
    //Components
    private JPanel serverPanel;
    private JButton serverButton;
    private JTextArea console;


    private Font componentsFont = new Font("Arial",Font.BOLD,16);
    private Font textAreaFont = new Font("Arial",Font.PLAIN,14);

    public ServerFrame(){

        //Components Setup
        setUpComponents();
        add(serverPanel);
        setSize(1024,768);

        //Frame setup
        setTitle("Server Application");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void setUpComponents(){

        //Button setup
        serverButton.setBackground(Color.WHITE);
        serverButton.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        serverButton.setFont(componentsFont);

        //TextArea setup
        console.setFont(textAreaFont);


    }

}
