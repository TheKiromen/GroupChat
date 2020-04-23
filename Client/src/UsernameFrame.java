import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UsernameFrame extends JFrame {
    private JTextField textField;
    private JButton connectButton;
    private JLabel enterLabel;
    private JPanel inputPanel;
    private String name="default";

    private Font componentsFont = new Font("Arial", Font.BOLD, 16);
    private Color fg= new Color(217, 208, 195);
    private Border spacing = BorderFactory.createEmptyBorder(8, 8, 8, 8);

    public UsernameFrame() {

        setUpComponents();
        add(inputPanel);
        setTitle("Welcome!");
        setSize(400, 130);
        setMinimumSize(new Dimension(400,130));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);


        connectButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                name = textField.getText();
            }
        });
    }

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
       // connectButton.setBorder(spacing);


    }

     public String getName() {return this.name;}
}
