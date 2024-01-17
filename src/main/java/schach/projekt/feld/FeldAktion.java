package schach.projekt.feld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FeldAktion implements ActionListener {

    final Feld feld;
    final Color origFarbe;
    final Color neueFarbe;

    private int zähler = 0;
    public FeldAktion(Feld feld) {
        this.feld = feld;
        this.origFarbe = feld.getBackground();
        this.neueFarbe = new Color(218, 196,49);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(zähler % 2 == 0){
            feld.setBackground(this.neueFarbe);
        }
        else feld.setBackground(this.origFarbe);

        zähler++;
    }
}
