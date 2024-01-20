package schach.projekt.feld;

import schach.projekt.schachFiguren.Schachfiguren;

import javax.swing.*;
import java.awt.*;

public class Feld extends JButton {

    private final int zeile;
    private final int spalte;

    private Schachfiguren figurAufFeld;

    public Feld(int spalte, int zeile){
        this.spalte = spalte;
        this.zeile = zeile;
        int index = zeile * 8 + spalte;
        Color blue = new Color(71, 117, 171);
        Color white = new Color(214, 221, 229);
        this.setBackground(((index + (double) (index / 8)) % 2 != 0) ? blue : white);
        this.addActionListener(new FeldAktion(this));
        this.setBorderPainted(false);

        this.setOpaque(true);
    }
    public Schachfiguren getFigurAufFeld() {
        return figurAufFeld;
    }

    public void setFigurAufFeld(Schachfiguren figurAufFeld) {
        this.figurAufFeld = figurAufFeld;
    }

    public int getXPos(int breiteProFeld) {
        return spalte * breiteProFeld;
    }

    public int getYPos(int laengeProFeld) {
        return zeile * laengeProFeld;
    }

}
