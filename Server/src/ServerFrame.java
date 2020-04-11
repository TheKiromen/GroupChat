
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
    private MyButton myButton1;

    private Font componentsFont = new Font("Arial", Font.BOLD, 16);
    private Font textAreaFont = new Font("Arial", Font.PLAIN, 14);
    private Border spacing = BorderFactory.createEmptyBorder(50, 0, 0, 0);

    public ServerFrame() {

        //Components Setup
        setUpComponents();
        add(serverPanel);
        setSize(820, 614);
        setMinimumSize(new Dimension(820, 614));

        //Frame setup
        setTitle("Server Application");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void setUpComponents() {

        //Button setup
        serverButton.setBackground(Color.getHSBColor(0, 0.01f, 0.20f));
        serverButton.setForeground(Color.getHSBColor(35, 0.01f, 0.85f));
        serverButton.setBorder(BorderFactory.createRaisedBevelBorder());
        serverButton.setFocusable(false);
        serverButton.setFont(componentsFont);

        //TextArea setup
        console.setFont(textAreaFont);
        console.setForeground(Color.getHSBColor(35, 0.01f, 0.85f));
        console.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));


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

    private void createUIComponents(){
        myButton1 = new MyButton("Witam");
        myButton1.setContentAreaFilled(false);
        myButton1.setFocusable(false);
        myButton1.setForeground(Color.getHSBColor(35, 0.01f, 0.85f));
        myButton1.setFont(new Font("Arial",Font.BOLD,16));
    }
}
