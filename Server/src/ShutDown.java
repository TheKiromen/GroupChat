import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ShutDown extends JFrame {

    private JLabel questionLabel;
    private MyButton yesButton;
    private MyButton noButton;
    private JPanel ShutDown;
    private int result=2;

    private Font componentsFont = new Font("Arial", Font.BOLD, 16);
    private Color fg= new Color(217, 208, 195);

    public ShutDown(ServerFrame f) {

        setUpComponents();
        add(ShutDown);
        setSize(400, 180);
        setMinimumSize(new Dimension(400, 180));

        setTitle("WARNING: Server shutdown!");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        yesButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                f.shutDown();
                dispose();
            }
        });
        noButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
    }

    public int getResult() {return result;}

    private void setUpComponents() {

        questionLabel.setFont(componentsFont);
        questionLabel.setForeground(fg);
        questionLabel.setBorder(BorderFactory.createEmptyBorder(0,0,30,0));

        //YesButton setup
        yesButton.setForeground(fg);
        yesButton.setFocusable(false);
        yesButton.setBackground(Color.getHSBColor(60, 0.12f, 0.22f));
        yesButton.setFont(componentsFont);
        yesButton.setBorder(BorderFactory.createRaisedBevelBorder());

        //NoButton setup
        noButton.setForeground(fg);
        noButton.setFocusable(false);
        noButton.setBackground(Color.getHSBColor(60, 0.12f, 0.22f));
        noButton.setBorder(BorderFactory.createRaisedBevelBorder());
        noButton.setFont(componentsFont);

    }

    //Create and setup custom components
    private void createUIComponents(){
        yesButton = new MyButton();
        yesButton.setContentAreaFilled(false);
        yesButton.setFocusable(false);

        noButton = new MyButton();
        noButton.setContentAreaFilled(false);
        noButton.setFocusable(false);
    }
}
