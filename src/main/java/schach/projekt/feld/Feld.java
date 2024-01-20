package schach.projekt.feld;

import javax.swing.*;
import java.awt.*;

public class Feld extends JButton {
    public Feld(int index){
        Color darkBrown = new Color(71, 117, 171);
        Color lightBrown = new Color(214, 221, 229);
        this.setBackground(((index + Math.floor(index / 8)) % 2 != 0) ? darkBrown : lightBrown);
        this.addActionListener(new FeldAktion(this));
        this.setBorderPainted(false);

        this.setOpaque(true);
    }

}
