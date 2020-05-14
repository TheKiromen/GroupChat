import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ChangeChatroom extends JFrame {
    //Component Variables
    private JList chatroomList;
    private MyButton changeButton;
    private JPanel chatroomsPanel;
    private JLabel infoLabel;
    private Border spacing = BorderFactory.createEmptyBorder(8, 8, 8, 8);
    private String[] chatrooms;

    //Fonts
    private Font textAreaFont = new Font("Arial", Font.PLAIN, 14);
    private Font componentsFont = new Font("Arial", Font.BOLD, 16);
    private Color fg = new Color(217, 208, 195);

    private ObjectOutputStream outToServer;


    //Constructor
    public ChangeChatroom(String[] chatrooms, ObjectOutputStream o){
        //Frame setup
        this.chatrooms=chatrooms;
        outToServer=o;
        setUpComponents();
        add(chatroomsPanel);
        setTitle("Choose a chatroom");
        setResizable(false);
        setSize(500, 500);
        setMinimumSize(new Dimension(500,500));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    /**
     * Configures components before adding them to frame.
     */
    private void setUpComponents() {

        //Chatrooms List setup
        chatroomList.setFont(textAreaFont);
        chatroomList.setBorder(spacing);
        chatroomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        chatroomList.setLayoutOrientation(JList.VERTICAL);
        chatroomList.setVisibleRowCount(5);
        chatroomList.setListData(chatrooms);

        //Labels
        infoLabel.setFont(componentsFont);
        infoLabel.setForeground(fg);
        infoLabel.setBorder(spacing);

        //Buttons
        changeButton.setFocusable(false);
        changeButton.setFont(componentsFont);
        changeButton.setForeground(fg);
        changeButton.setBackground(Color.getHSBColor(60, 0.12f, 0.22f));
        changeButton.setBorder(BorderFactory.createRaisedBevelBorder());
        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    outToServer.writeObject(new Request(RequestType.CHATROOM_CHANGE,chatrooms[chatroomList.getSelectedIndex()]));
                    outToServer.flush();
                    ChangeChatroom.this.dispose();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void createUIComponents(){
        changeButton = new MyButton("Create");
        changeButton.setContentAreaFilled(false);
        changeButton.setFocusable(false);
    }

}
