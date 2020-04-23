import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ShutDown extends JFrame {

    private JLabel questionLabel;
    private JButton yesButton;
    private JButton noButton;
    private JPanel ShutDown;
    private int result=2;

    private Font componentsFont = new Font("Arial", Font.BOLD, 16);
    private Color fg= new Color(217, 208, 195);

    public ShutDown() {

        setUpComponents();
        add(ShutDown);
        setSize(400, 120);
        setMinimumSize(new Dimension(400, 120));

        setTitle("WARNING: Server shutdown!");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        yesButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                result=1;
                result=getResult();
            }
        });
        noButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                result=0;
                result=getResult();
            }
        });
    }

    public int getResult() {return result;}

    private void setUpComponents() {

        questionLabel.setFont(componentsFont);
        questionLabel.setForeground(fg);

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
}
