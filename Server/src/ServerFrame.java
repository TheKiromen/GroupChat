
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ServerFrame extends JFrame {

    //\/ These are managed by Intellij's GUI designer
    //Components
    private JPanel serverPanel;
    private JButton serverButton;
    private JTextArea console;
    private JLabel serverStatus;
    private JLabel status;
    private JLabel ip;
    private JLabel port;


    private Font componentsFont = new Font("Arial",Font.BOLD,16);
    private Font textAreaFont = new Font("Arial",Font.PLAIN,14);
    private Border spacing = BorderFactory.createEmptyBorder(50,0,0,0);

    public ServerFrame(){

        //Components Setup
        setUpComponents();
        add(serverPanel);
        setSize(820,614);
        setMinimumSize(new Dimension(820,614));

        //Frame setup
        setTitle("Server Application");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);
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


        //Labels setup
        serverStatus.setBorder(spacing);
        serverStatus.setFont(componentsFont);
        status.setFont(componentsFont);
        status.setForeground(Color.RED);

        ip.setFont(componentsFont);
        ip.setBorder(spacing);

        port.setFont(componentsFont);
        port.setBorder(spacing);
    }

}
