package schach.projekt.GameManager;

import schach.projekt.schachFiguren.Schachfiguren;
import schach.projekt.schachFiguren.Teams;
import schach.projekt.feld.Feld;
import schach.projekt.schachFiguren.figuren.Bauer;
import schach.projekt.Schachbrett.Schachbrett;
import schach.projekt.schachFiguren.figuren.Koenigin;

import java.util.HashMap;
import java.util.ArrayList;


public class GameManager {

    private static Teams zug = Teams.WHITE;

    public static HashMap<Integer, ArrayList<Feld>> gueltigerZug = new HashMap<>();
    private static final ArrayList<Rochade> ROCHADE = new ArrayList<>();
    private static final ArrayList<EnPassant> ENPASSANT = new ArrayList<>();

    public static void progressTurn(){
        zug = (zug == Teams.WHITE) ? Teams.BLACK : Teams.WHITE;

        for(EnPassant enPassant : ENPASSANT){
            if(enPassant.feldEntfernen.getFigurAufFeld() instanceof Bauer){
                ((Bauer) enPassant.feldEntfernen.getFigurAufFeld()).setKannEinEnPassantSeinInZukunft(false);
            }
        }
    }

    public static Teams getZug() {
        return zug;
    }

    public static void addRochade(Rochade rochade){
        ROCHADE.add(rochade);
    }

    public static Rochade getRochade(Feld neuesFeldKoenig){
        for(Rochade zug : ROCHADE){
            if(zug.neuesFeldKoenig.equals(neuesFeldKoenig)){
                return zug;
            }
        }
        return null;
    }

    public static Feld getEnPassant(Feld zielFeld){
        //Returns the taken Field
        for(EnPassant zug : ENPASSANT){
            if(zug.zielFeld.equals(zielFeld) && zug.zielFeld.getFigurAufFeld() instanceof Bauer){
                return zug.feldEntfernen;
            }
        }
        return null;
    }

    public static void istRochade() {
        ROCHADE.clear();
    }

    public static void istEnPassant() {
        System.out.println("En passant moves cleared");
        ENPASSANT.clear();
    }

    public static void addEnPassant(EnPassant enPassant) {
        ENPASSANT.add(enPassant);
    }

    public static Teams getAktuellesTeam() {
        return zug = (zug == Teams.WHITE) ? Teams.BLACK : Teams.WHITE;
    }

    public static void ueberpruefeBauernUmwandlung() {
        Teams aktuellesTeam = getAktuellesTeam();
        Feld[] letzteReihe = (aktuellesTeam == Teams.WHITE)
                ? new Feld[]{Schachbrett.felder[0], Schachbrett.felder[1], Schachbrett.felder[2], Schachbrett.felder[3],
                Schachbrett.felder[4], Schachbrett.felder[5], Schachbrett.felder[6], Schachbrett.felder[7]}
                : new Feld[]{Schachbrett.felder[56], Schachbrett.felder[57], Schachbrett.felder[58], Schachbrett.felder[59],
                Schachbrett.felder[60], Schachbrett.felder[61], Schachbrett.felder[62], Schachbrett.felder[63]};


        for (Feld feld : letzteReihe) {
            Schachfiguren figur = feld.getFigurAufFeld();
            if (figur instanceof Bauer && figur.getTeam() == aktuellesTeam) {
                int feldId = feld.getFeldId();
                if ((aktuellesTeam == Teams.WHITE && feldId >= 0 && feldId <= 7) ||
                        (aktuellesTeam == Teams.BLACK && feldId >= 56 && feldId <= 63)) {


                    Koenigin neueFigur = new Koenigin(aktuellesTeam, feld);
                    feld.setFigurAufFeld(neueFigur);


                }
            }
        }
    }
}