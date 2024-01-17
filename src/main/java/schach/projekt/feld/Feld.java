package schach.projekt.feld;

import javax.swing.*;
import java.awt.*;

public class Feld extends JButton {
    public Feld(int index){
        Color darkBrown = new Color(185,139,98);
        Color lightBrown = new Color(242, 218, 179);
        this.setBackground(((index + Math.floor(index / 8)) % 2 != 0) ? darkBrown : lightBrown);
        this.addActionListener(new FeldAktion(this));
        this.setBorderPainted(false);
    }

}
