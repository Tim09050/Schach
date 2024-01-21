package schach.projekt.feld;

import schach.projekt.Schachbrett.Schachbrett;
import schach.projekt.schachFiguren.Schachfiguren;

import javax.swing.*;
import java.awt.*;

public class Feld extends JButton {

    private final int zeile;
    private final int spalte;
    private boolean ausgewaehlt;
    private final int feldId;
    private final Color origFarbe;
    private final Color neueFarbe;
    private boolean kannBewegen;
    private final FeldVisualisierer feldVisualisierer;
    private Schachfiguren figurAufFeld;

    public Feld(int spalte, int zeile){
        this.spalte = spalte;
        this.zeile = zeile;
        this.neueFarbe = new Color(218, 196,49);
        int index = zeile * 8 + spalte;
        Color blue = new Color(71, 117, 171);
        Color white = new Color(214, 221, 229);
        this.setBackground(((index + (double) (index / 8)) % 2 != 0) ? blue : white);
        this.origFarbe = this.getBackground();
        this.addActionListener(new FeldAktion(this));
        this.setBorderPainted(false);
        this.setOpaque(true);

        this.feldId = index;
        this.feldVisualisierer = new FeldVisualisierer(this);
        this.feldVisualisierer.setVisible(false);
        this.add(new JLabel(String.valueOf(feldId)));
    }
    public Schachfiguren getFigurAufFeld() {
        return figurAufFeld;
    }

    public void setFigurAufFeld(Schachfiguren figurAufFeld) {
        this.figurAufFeld = figurAufFeld;
    }

    public boolean isAusgewaehlt() {
        return ausgewaehlt;
    }

    public void setAusgewaehlt(boolean ausgewaehlt) {
        this.ausgewaehlt = ausgewaehlt;
        Schachbrett.setAusgewaehltesFeld(((ausgewaehlt) ? this : null));
        setBackground((ausgewaehlt) ? this.neueFarbe : this.origFarbe);
        if (ausgewaehlt) {
            for (Feld gueltigerZug : this.getFigurAufFeld().getGueltigenZug()) {
                gueltigerZug.machFeldBewegbar();
            }
        } else {
            for (Feld gueltigerZug :this.getFigurAufFeld().getGueltigenZug()) {
                gueltigerZug.machFeldUnbewegbar();
            }
        }
    }
    public void entferneFigur(){
        if(this.getFigurAufFeld() != null){
            Schachbrett.getSchachfigurPanel().entferneFigurVomBrett(this.getFigurAufFeld());
        }
    }


    public int getXPos(int breiteProFeld) {
        return spalte * breiteProFeld;
    }

    public int getYPos(int laengeProFeld) {
        return zeile * laengeProFeld;
    }

    public int getZeile() {
        return zeile;
    }

    public int getSpalte() {
        return spalte;
    }

    public int getFeldId() {
        return feldId;
    }

    public boolean isKannBewegen() {
        return kannBewegen;
    }

    public FeldVisualisierer getFeldVisualisierer() {
        return feldVisualisierer;
    }

    public void machFeldBewegbar() {
        this.feldVisualisierer.updateDatei();
        this.feldVisualisierer.setVisible(true);
        kannBewegen = true;
    }

    public void machFeldUnbewegbar() {
        this.feldVisualisierer.setVisible(false);
        kannBewegen = false;
    }
}
