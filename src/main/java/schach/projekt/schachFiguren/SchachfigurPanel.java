package schach.projekt.schachFiguren;


import schach.projekt.Schachbrett.Schachbrett;
import schach.projekt.feld.Feld;
import schach.projekt.schachFiguren.figuren.*;

import javax.swing.*;
import java.util.ArrayList;

public class SchachfigurPanel extends JLayeredPane {


    public static ArrayList<Schachfiguren> figuren = new ArrayList<>();
    public SchachfigurPanel(){
        this.generiereSchachbrett("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
    }


    private void generiereSchachbrett(String FEN) {
        String[] FENGroups = FEN.split(" ");
        int position = 0;
        char[] figurPosition = FENGroups[0].toCharArray();
        for(char c : figurPosition){
            if(c == '/') continue;
            if(Character.isDigit(c)){
                position += Character.getNumericValue(c);
                continue;
            }
            Teams team = (Character.isLowerCase(c)) ? Teams.BLACK : Teams.WHITE;
            Schachfiguren figur = null;
            switch (Character.toLowerCase(c)) {
                case 'r' -> { figur = new Turm(team, Schachbrett.felder[position]);}
                case 'n' -> { figur = new Springer(team, Schachbrett.felder[position]);}
                case 'b' -> { figur = new Laeufer(team, Schachbrett.felder[position]);}
                case 'q' -> { figur = new Koenigin(team, Schachbrett.felder[position]);}
                case 'k' -> { figur = new Koenig(team, Schachbrett.felder[position]);}
                case 'p' -> { figur = new Bauer(team, Schachbrett.felder[position]);}
            }
            figuren.add(figur);
            Schachbrett.felder[position].setFigurAufFeld(figur);
            position++;
        }

        for(Feld feld : Schachbrett.felder){
            this.setLayer(feld.getFeldVisualisierer(), 1);
            feld.getFeldVisualisierer().setBounds(feld.getXPos(Schachbrett.BREITE / 8), feld.getYPos(Schachbrett.HOEHE / 8), Schachbrett.BREITE / 8, Schachbrett.HOEHE / 8);
            this.add(feld.getFeldVisualisierer());
        }

        for(Schachfiguren f : figuren){
            this.setLayer(f, 2);
            f.setBounds(f.getJetzigesFeld().getXPos(Schachbrett.BREITE/8), f.getJetzigesFeld().getYPos(Schachbrett.HOEHE /8), Schachbrett.BREITE/8, Schachbrett.HOEHE /8);
            this.add(f);
        }
    }

    public void entferneFigurVomBrett(Schachfiguren schachfiguren){
        figuren.remove(schachfiguren);
        this.remove(schachfiguren);
        this.repaint();
    }

    @Override
    public void repaint() {
        super.repaint();

        for(Feld feld : Schachbrett.felder){
            feld.getFeldVisualisierer().setBounds(feld.getXPos(this.getWidth() / 8), feld.getYPos(this.getHeight() / 8), this.getWidth() / 8, this.getHeight() / 8);
        }

        for(Schachfiguren f : figuren){
            f.setBounds(f.getJetzigesFeld().getXPos(this.getWidth()/8), f.getJetzigesFeld().getYPos(this.getHeight()/8), this.getWidth()/8, this.getHeight()/8);
        }
    }
}
