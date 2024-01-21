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


}