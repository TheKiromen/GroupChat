import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsernameFrame extends JFrame {

    //Component Variables
    private JTextField textField;
    private MyButton connectButton;
    private JLabel enterLabel;
    private JPanel inputPanel;
    private Border spacing = BorderFactory.createEmptyBorder(8, 8, 8, 8);

    //Fonts
    private Font componentsFont = new Font("Arial", Font.BOLD, 16);
    private Color fg= new Color(217, 208, 195);

    //Program Variables
    private Pattern regex = Pattern.compile("^[a-zA-Z0-9]*$");
    private Matcher matcher;
    private Action a = new Action();

    //-----------------------------MAIN METHOD-------------------------//
    public static void main(String[] args) {
        new UsernameFrame();
    }

    //Constructor
    public UsernameFrame() {

        //Frame setup
        setUpComponents();
        add(inputPanel);
        setTitle("Welcome!");
        setSize(400, 130);
        setMinimumSize(new Dimension(400,130));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);


        connectButton.addActionListener(a);

        textField.addActionListener(a);


    }

    /**
     * Configures components before adding them to frame.
     */
    private void setUpComponents() {

        textField.setFont(componentsFont);
        textField.setBorder(spacing);
        enterLabel.setFont(componentsFont);
        enterLabel.setForeground(fg);
        enterLabel.setBorder(spacing);
        connectButton.setFocusable(false);
        connectButton.setFont(componentsFont);
        connectButton.setForeground(fg);
        connectButton.setBackground(Color.getHSBColor(60, 0.12f, 0.22f));
        connectButton.setBorder(BorderFactory.createRaisedBevelBorder());

    }

    /**
     * Initializes custom components
     */
    //Create and setup custom components
    private void createUIComponents(){
        connectButton = new MyButton();
        connectButton.setContentAreaFilled(false);
        connectButton.setFocusable(false);
    }

    //Internal class that defines button behavior
    private class Action implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            String tmp=textField.getText();
            matcher=regex.matcher(tmp);

            //Check if username is valid
            if(tmp.length()>=4 && tmp.length()<=20){
                if(matcher.matches()){
                    new ChatWindow(textField.getText());
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(null,"Username can contain only letters and numbers.");
                }
            }else{
                JOptionPane.showMessageDialog(null,"Username should be 4 to 20 characters long.");
            }

        }
    }

}
