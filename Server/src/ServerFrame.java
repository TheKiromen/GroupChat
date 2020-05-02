import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerFrame extends JFrame {

    //Component Variables
    private MyButton mainButton;
    private JPanel serverPanel;
    private JTextArea console;
    private JLabel serverStatus;
    private JLabel status;
    private JLabel ip;
    private JLabel port;
    private JLabel authors;

    //Fonts
    private Font componentsFont = new Font("Arial", Font.BOLD, 16);
    private Font textAreaFont = new Font("Arial", Font.PLAIN, 14);

    //Borders
    private Border spacing = BorderFactory.createEmptyBorder(50, 0, 0, 0);

    //Program Variables
    public boolean isRunning = false;
    private ServerFrame frame;
    private ExecutorService ex = Executors.newSingleThreadExecutor();


    //-----------------------------MAIN METHOD-------------------------//
    public static void main(String[] args) {
        new ServerFrame();
    }


    //Constructor
    public ServerFrame() {

        frame=this;

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

    /**
     * Configures components before adding them to frame.
     */
    //Set up Java components
    private void setUpComponents() {

        //TextArea setup
        console.setFont(textAreaFont);
        console.setForeground(Color.getHSBColor(35, 0.01f, 0.85f));
        console.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        console.setCaretColor(Color.WHITE);
        console.setEditable(false);

        //Button setup
        mainButton.setText("Start Server");
        mainButton.setPreferredSize(new Dimension(150,60));
        mainButton.setMinimumSize(new Dimension(150,60));
        mainButton.setForeground(Color.getHSBColor(35, 0.01f, 0.85f));
        mainButton.setFont(componentsFont);

        //Button behavior
        mainButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //Stop server
                if(isRunning){

                    new ShutDown(frame);

                //Start Server
                }else{
                    isRunning=true;

                    //Button
                    console.append("Server starting... \n");
                    mainButton.setText("Stop Server");

                    //Status label
                    status.setText("Online");
                    status.setForeground(Color.GREEN);

                    //Start main Thread
                    ex.execute(new MainApp(frame));

                }
            }
        });

        //Labels setup
        //Status label
        serverStatus.setBorder(spacing);
        serverStatus.setFont(componentsFont);
        status.setFont(componentsFont);
        status.setForeground(Color.RED);

        //Ip label
        ip.setFont(componentsFont);
        ip.setBorder(spacing);

        //Port label
        port.setFont(componentsFont);
        port.setBorder(spacing);

        //Authors label
        authors.setFont(new Font("Arial",Font.PLAIN,12));
        authors.setBorder(BorderFactory.createEmptyBorder(0,10,10,0));

    }

    /**
     * Initialize custom components
     */
    //Create and setup custom components
    private void createUIComponents(){
        mainButton = new MyButton();
        mainButton.setContentAreaFilled(false);
        mainButton.setFocusable(false);
    }

    /**
     * Method to display text on server console
     * @param text text you want to display
     */
    //Write text to textArea
    public void writeToConsole(String text){
        console.append(text+"\n");
    }

    /**
     * Method to safely shut down the server
     */
    //Shut down the server
    public void shutDown(){
        isRunning=false;

        //Button
        console.append("Server is shutting down... \n");
        mainButton.setText("Start Server");
        mainButton.setEnabled(false);

        //Status label
        status.setText("Offline");
        status.setForeground(Color.RED);

        //Close program after delay
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    System.exit(0);
                } catch (InterruptedException exc) {
                    exc.printStackTrace();
                }
            }
        });
    }



}
