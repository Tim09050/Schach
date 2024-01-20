package schach.projekt.schachFiguren;



import schach.projekt.Schachbrett;
import schach.projekt.schachFiguren.figuren.Koenig;

import javax.swing.*;
import java.awt.*;

import static schach.projekt.Schachbrett.*;

public class SchachfigurPanel extends JLayeredPane {

    Koenig koenig;
    public SchachfigurPanel(){
        this.setPreferredSize(new Dimension(Schachbrett.BREITE,Schachbrett.LAENGE));
        initSchachbrett();
    }

    private void initSchachbrett(){
        koenig = new Koenig(Teams.BLACK);
        koenig.setBounds(0, 0, BREITE /8, LAENGE /8);
        this.setLayer(koenig, 1);
        this.add(koenig);
    }

    @Override
    public void repaint() {
        super.repaint();
        koenig.setBounds(0, 0, this.getWidth()/8, this.getHeight()/8);
    }
}
