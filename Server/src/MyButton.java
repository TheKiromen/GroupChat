import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;

public class MyButton extends JButton {


    //TODO Pick good colors
    private Color backgroundPressed = Color.getHSBColor(0.5f, 0.02f, 0.30f);
    private Color backgroundIdle = Color.getHSBColor(0, 0.01f, 0.20f);
    private Color c1 = Color.RED;
    private Color c2 = Color.BLUE;
    private Border inner = BorderFactory.createBevelBorder(BevelBorder.LOWERED, c1,c1,c2,c2);
    private Border outer = BorderFactory.createBevelBorder(BevelBorder.RAISED, c1,c1,c2,c2);

    MyButton(){
        super();
    }

    MyButton(String a){
        super(a);
    }

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
