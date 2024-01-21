package schach.projekt.feld;

import schach.projekt.GameManager.GameManager;
import schach.projekt.GameManager.Rochade;
import schach.projekt.Schachbrett.Schachbrett;
import schach.projekt.schachFiguren.SchachfigurPanel;
import schach.projekt.schachFiguren.Schachfiguren;
import schach.projekt.schachFiguren.Teams;
import schach.projekt.schachFiguren.figuren.Koenig;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FeldAktion implements ActionListener {

    final Feld feld;

    private int zähler = 0;
    public FeldAktion(Feld feld) {
        this.feld = feld;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(feld.isKannBewegen()){
            Feld startfeld = Schachbrett.getAusgewaehltesFeld();
            boolean koenigBew = startfeld.getFigurAufFeld() instanceof Koenig;
            bewegeFigur(startfeld);
            checkEnPassant();
            if(koenigBew){
                checkRochade();
            }
            Schachbrett.getSchachfigurPanel().repaint();
            GameManager.progressTurn();
        }
        if(feld.getFigurAufFeld() == null && Schachbrett.getAusgewaehltesFeld() != null) {
            Schachbrett.getAusgewaehltesFeld().setAusgewaehlt(false);
        }
        if(feld.getFigurAufFeld() != null && GameManager.getZug() == feld.getFigurAufFeld().getTeam()) {
            if (feld.getFigurAufFeld() != null && Schachbrett.getAusgewaehltesFeld() != null && !feld.isAusgewaehlt()) {
                Schachbrett.getAusgewaehltesFeld().setAusgewaehlt(false);
                feld.setAusgewaehlt(true);
            } else if (feld.getFigurAufFeld() != null) {
                feld.setAusgewaehlt(!feld.isAusgewaehlt());
            }
        }

        if(istImSchach(Teams.WHITE)) {
            System.out.println("Weißer König im Schach!");
            if(istSchachmatt(Teams.WHITE)) {
                System.out.println("Schachmatt! Spiel vorbei. Schwarz gewinnt");
            }
        }
        if(istImSchach(Teams.BLACK)) {
            System.out.println("Schwarze König im Schach!");
            if(istSchachmatt(Teams.BLACK)) {
                System.out.println("Schachmatt! Spiel vorbei. Weiß gewinnt");
            }
        }

        GameManager.ueberpruefeBauernUmwandlung();

    }

    public boolean istImSchach(Teams team) {
        for (Schachfiguren figur : SchachfigurPanel.figuren) {
            if (figur.getTeam() != team) {
                ArrayList<Feld> gueltigeZuege = figur.getGueltigenZug();
                for (Feld zug : gueltigeZuege) {
                    if (zug.getFigurAufFeld() instanceof Koenig && zug.getFigurAufFeld().getTeam() == team) {
                        // Der König steht im Schach
                        return true;
                    }
                }
            }
        }

        // Der König steht nicht im Schach
        return false;
    }

    public boolean istSchachmatt(Teams team) {
        if (!istImSchach(team)) {
            return false;
        }
        for (Feld feld : Schachbrett.felder) {
            if (feld.getFigurAufFeld() == null || feld.getFigurAufFeld().getTeam() != team) {
                Schachfiguren koenig = getKoenig(team);
                assert koenig != null;
                Feld jetzigesFeld = koenig.getJetzigesFeld();
                koenig.bewegeZuEinemNeuemFeld(feld);
                if (!istImSchach(team)) {
                    koenig.bewegeZuEinemNeuemFeld(jetzigesFeld);
                    return false;
                }
                koenig.bewegeZuEinemNeuemFeld(jetzigesFeld);
            }
        }

        // Kein möglicher Zug, der den König aus dem Schach herausbewegt
        return true;
    }

    private Schachfiguren getKoenig(Teams team) {
        for (Schachfiguren figur : SchachfigurPanel.figuren) {
            if (figur instanceof Koenig && figur.getTeam() == team) {
                return figur;
            }
        }
        return null;
    }

    private void checkEnPassant() {
        Feld enPassant = GameManager.getEnPassant(feld);
        if(enPassant != null){
            enPassant.entferneFigur();
            enPassant.setFigurAufFeld(null);
        }
        GameManager.istEnPassant();
    }

    private void checkRochade() {
        Rochade rochade = GameManager.getRochade(feld);
        if(rochade != null){
            rochade.turmZurRochade.bewegeZuEinemNeuemFeld(rochade.neuesFeldTurm);
            GameManager.istRochade();
        }
    }

    public void bewegeFigur(Feld startFeld){
        Schachfiguren figurZumBewegen = Schachbrett.getAusgewaehltesFeld().getFigurAufFeld();
        feld.entferneFigur();

        startFeld.setAusgewaehlt(false);
        figurZumBewegen.bewegeZuEinemNeuemFeld(feld);
        figurZumBewegen.bewegt();
    }
}
