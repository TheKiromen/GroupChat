import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;

/**
 *Allows customizing all the buttons in this application.
 * Buttons change appearance when pressed.
 */
public class MyButton extends JButton {

    //Button Colors
    private Color backgroundPressed = Color.getHSBColor(0.5f, 0.02f, 0.15f);
    private Color backgroundIdle = Color.getHSBColor(0, 0.01f, 0.20f);
    private Color c1 = Color.getHSBColor(35, 0.01f, 0.55f);
    private Color c2 = Color.BLACK;

    //Button borders
    private Border inner = BorderFactory.createBevelBorder(BevelBorder.LOWERED, c1,c1,c2,c2);
    private Border outer = BorderFactory.createBevelBorder(BevelBorder.RAISED, c1,c1,c2,c2);

    //Constructors
    MyButton(){
        super();
    }

    MyButton(String a){
        super(a);
    }

    /**
     * Allows an application to paint the button.
     * @param g object of the abstract base class for graphics contexts.
     */
    //Button behavior
    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(backgroundPressed);
            setBorder(inner);
        } else {
            g.setColor(backgroundIdle);
            setBorder(outer);
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }


}
