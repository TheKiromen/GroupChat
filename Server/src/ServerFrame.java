
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ServerFrame extends JFrame {

    //\/ These are managed by Intellij's GUI designer
    //Components
    private JPanel serverPanel;
    private JTextArea console;
    private JLabel serverStatus;
    private JLabel status;
    private JLabel ip;
    private JLabel port;
    private MyButton myButton1;

    //Fonts
    private Font componentsFont = new Font("Arial", Font.BOLD, 16);
    private Font textAreaFont = new Font("Arial", Font.PLAIN, 14);

    //Borders
    private Border spacing = BorderFactory.createEmptyBorder(50, 0, 0, 0);

    //Variables
    private boolean isRunning = false;

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

    //Set up Java components
    private void setUpComponents() {

        //TextArea setup
        console.setFont(textAreaFont);
        console.setForeground(Color.getHSBColor(35, 0.01f, 0.85f));
        console.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        console.setCaretColor(Color.WHITE);
        console.setEditable(false);

        myButton1.setText("Start Server");
        myButton1.setForeground(Color.getHSBColor(35, 0.01f, 0.85f));
        myButton1.setFont(componentsFont);
        myButton1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(isRunning){
                    //Button
                    console.append("Server is shutting down... \n");
                    myButton1.setText("Start Server");

                    //Status label
                    status.setText("Offline");
                    status.setForeground(Color.RED);

                    isRunning=false;
                }else{
                    //Button
                    console.append("Server starting... \n");
                    myButton1.setText("Stop Server");

                    //Status label
                    status.setText("Online");
                    status.setForeground(Color.GREEN);

                    isRunning=true;
                }
            }
        });

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

    //Create and setup custom components
    private void createUIComponents(){
        myButton1 = new MyButton();
        myButton1.setContentAreaFilled(false);
        myButton1.setFocusable(false);

    }
}
