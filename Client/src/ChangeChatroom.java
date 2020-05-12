import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ChangeChatroom extends JFrame {
    //Component Variables
    private JList chatroomList;
    private JButton changeButton;
    private JPanel chatroomsPanel;
    private JLabel infoLabel;
    private Border spacing = BorderFactory.createEmptyBorder(8, 8, 8, 8);

    //Fonts
    private Font textAreaFont = new Font("Arial", Font.PLAIN, 14);
    private Font componentsFont = new Font("Arial", Font.BOLD, 16);
    private Color fg = new Color(217, 208, 195);


    //Constructor
    public ChangeChatroom(){
        //Frame setup
        setUpComponents();
        add(chatroomsPanel);
        setTitle("Choose a chatroom");
        setResizable(false);
        setSize(500, 500);
        setMinimumSize(new Dimension(500,500));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    /**
     * Configures components before adding them to frame.
     */
    private void setUpComponents() {
        //Labels
        chatroomList.setFont(textAreaFont);
        chatroomList.setBorder(spacing);
        infoLabel.setFont(componentsFont);
        infoLabel.setForeground(fg);
        infoLabel.setBorder(spacing);

        //Buttons
        changeButton.setFocusable(false);
        changeButton.setFont(componentsFont);
        changeButton.setForeground(fg);
        changeButton.setBackground(Color.getHSBColor(60, 0.12f, 0.22f));
        changeButton.setBorder(BorderFactory.createRaisedBevelBorder());

    }
}
