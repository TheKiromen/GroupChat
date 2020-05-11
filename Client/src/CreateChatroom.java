import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class CreateChatroom extends JFrame {
    //Component Variables
    private JPanel creatingPanel;
    private JLabel nameLabel;
    private JTextField textField1;
    private JButton createButton;
    private Border spacing = BorderFactory.createEmptyBorder(8, 8, 8, 8);

    //Fonts
    private Font componentsFont = new Font("Arial", Font.BOLD, 16);
    private Color fg = new Color(217, 208, 195);

    private ObjectOutputStream outToServer;
    private Action a = new Action();

    //Constructor
    public CreateChatroom(ObjectOutputStream o) {
        //Frame setup
        outToServer=o;
        setUpComponents();
        add(creatingPanel);
        setTitle("Create new chatroom");
        setResizable(false);
        setSize(500, 150);
        setMinimumSize(new Dimension(500,150));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        textField1.addActionListener(a);
        createButton.addActionListener(a);


    }


    /**
     * Configures components before adding them to frame.
     */
    private void setUpComponents() {
        //Labels
        textField1.setFont(componentsFont);
        textField1.setBorder(spacing);
        nameLabel.setFont(componentsFont);
        nameLabel.setForeground(fg);
        nameLabel.setBorder(spacing);

        //Buttons
        createButton.setFocusable(false);
        createButton.setFont(componentsFont);
        createButton.setForeground(fg);
        createButton.setBackground(Color.getHSBColor(60, 0.12f, 0.22f));
        createButton.setBorder(BorderFactory.createRaisedBevelBorder());
    }

    private void createUIComponents(){
        createButton = new MyButton("Create");
        createButton.setContentAreaFilled(false);
        createButton.setFocusable(false);
    }


    private class Action implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                outToServer.writeObject(new Request(RequestType.CREATE_CHATROOM,textField1.getText()));
                outToServer.flush();
                CreateChatroom.this.dispose();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
