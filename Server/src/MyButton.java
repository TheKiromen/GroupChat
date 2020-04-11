import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton {

    MyButton(){
        super();
    }

    MyButton(String a){
        super(a);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(Color.RED);
            setBorder(BorderFactory.createLoweredBevelBorder());
        } else {
            g.setColor(Color.GREEN);
            setBorder(BorderFactory.createRaisedBevelBorder());
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }


}
