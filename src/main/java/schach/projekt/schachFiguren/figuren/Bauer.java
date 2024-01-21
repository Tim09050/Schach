package schach.projekt.schachFiguren.figuren;

import schach.projekt.GameManager.EnPassant;
import schach.projekt.Schachbrett.Schachbrett;
import schach.projekt.feld.Feld;
import schach.projekt.schachFiguren.Schachfiguren;
import schach.projekt.schachFiguren.Teams;
import schach.projekt.GameManager.GameManager;
import schach.projekt.schachFiguren.SchachfigurPanel;

import java.util.ArrayList;

public class Bauer extends Schachfiguren {

    public Bauer(Teams team, Feld startFeld) {
        super("schachfiguren/" + team.name().toLowerCase() + "_pawn.svg", team, startFeld);
    }

    public void umwandeln() {
        if ((this.getTeam() == Teams.WHITE && this.getJetzigesFeld().getZeile() == 0) ||
                (this.getTeam() == Teams.BLACK && this.getJetzigesFeld().getZeile() == 7)) {
            // Der Bauer steht auf dem letzten Feld, umwandeln zu Dame (Königin)
            Teams team = this.getTeam();
            Feld startFeld = this.getJetzigesFeld();
            Schachfiguren neueFigur = new Koenigin(team, startFeld);
            SchachfigurPanel schachfigurPanel = Schachbrett.getSchachfigurPanel();

            // Entferne den alten Bauer und füge die neue Figur hinzu
            schachfigurPanel.entferneFigurVomBrett(this);
            SchachfigurPanel.figuren.add(neueFigur);
            startFeld.setFigurAufFeld(neueFigur);

            // Aktualisiere die Darstellung (z.B., aktualisiere das SVG)
            schachfigurPanel.repaint();
        }
    }

    @Override
    public ArrayList<Feld> getGueltigenZug() {
        ArrayList<Feld> gueltigerZug = new ArrayList<>();

        Integer[] zugVektor = getMoveVectors();
        int index = this.getJetzigesFeld().getFeldId();
        for(int moveVector: zugVektor){
            int neuePos = index + moveVector;
            if(neuePos < 0 || neuePos > 63) break;
            if (Schachbrett.felder[neuePos].getFigurAufFeld() != null)
                break;
            gueltigerZug.add(Schachbrett.felder[neuePos]);
        }
        gueltigerZug.addAll(schlagen());
        return gueltigerZug;
    }

    private Integer[] getMoveVectors(){
        boolean istErsterZug = this.getJetzigesFeld().getZeile() == ((this.getTeam() == Teams.WHITE) ? 6 : 1);
        int team = (this.getTeam() == Teams.WHITE) ? -1 : 1;
        if(istErsterZug){
            return new Integer[]{8 * team, 16 * team};
        }
        return new Integer[]{8 * team};
    }

    public ArrayList<Feld> schlagen(){
        ArrayList<Feld> schlagen = new ArrayList<>();
        Feld jetzigesFeld = this.getJetzigesFeld();
        int teamPref = (this.getTeam() == Teams.WHITE) ? -1 : 1;

        //Normal takes
        if(jetzigesFeld.getFeldId() + 7 * teamPref >= 0 && jetzigesFeld.getFeldId() + 7 * teamPref <= 63){
            if(Schachbrett.felder[jetzigesFeld.getFeldId() + 7 * teamPref].getFigurAufFeld() != null && Schachbrett.felder[jetzigesFeld.getFeldId() + 7 * teamPref].getFigurAufFeld().getTeam() != this.getTeam()){
                if(jetzigesFeld.getSpalte() != ((this.getTeam() == Teams.BLACK) ? 0 : 7)){
                    schlagen.add(Schachbrett.felder[jetzigesFeld.getFeldId() + 7 * teamPref]);
                }
            }
        }
        if(jetzigesFeld.getFeldId() + 9 * teamPref >= 0 && jetzigesFeld.getFeldId() + 9 * teamPref <= 63){
            if(Schachbrett.felder[jetzigesFeld.getFeldId() + 9 * teamPref].getFigurAufFeld() != null && Schachbrett.felder[jetzigesFeld.getFeldId() + 9 * teamPref].getFigurAufFeld().getTeam() != this.getTeam()) {
                if(jetzigesFeld.getSpalte() != ((this.getTeam() == Teams.BLACK) ? 7 : 0)){
                    schlagen.add(Schachbrett.felder[jetzigesFeld.getFeldId() + 9 * teamPref]);
                }
            }
        }


        //En passant
        if(jetzigesFeld.getZeile() == ((this.getTeam() == Teams.WHITE) ? 3 : 4)){
            if(Schachbrett.felder[jetzigesFeld.getFeldId() + 1].getFigurAufFeld() instanceof Bauer && ((Bauer) Schachbrett.felder[jetzigesFeld.getFeldId() + 1].getFigurAufFeld()).kannEinEnPassantSein()){
                schlagen.add(Schachbrett.felder[jetzigesFeld.getFeldId() + ((this.getTeam() == Teams.BLACK) ? 9 : -7)]);
                EnPassant enPassant = new EnPassant(Schachbrett.felder[jetzigesFeld.getFeldId() + 1], Schachbrett.felder[jetzigesFeld.getFeldId() + ((this.getTeam() == Teams.BLACK) ? 9 : -7)]);
                GameManager.addEnPassant(enPassant);
                System.out.println("New en passant move added");
            }
            if(Schachbrett.felder[jetzigesFeld.getFeldId() - 1].getFigurAufFeld() instanceof Bauer && ((Bauer) Schachbrett.felder[jetzigesFeld.getFeldId() - 1].getFigurAufFeld()).kannEinEnPassantSein()){
                schlagen.add(Schachbrett.felder[jetzigesFeld.getFeldId() + ((this.getTeam() == Teams.BLACK) ? 7 : -9)]);
                EnPassant enPassant = new EnPassant(Schachbrett.felder[jetzigesFeld.getFeldId() - 1], Schachbrett.felder[jetzigesFeld.getFeldId() + ((this.getTeam() == Teams.BLACK) ? 7 : -9)]);
                GameManager.addEnPassant(enPassant);
                System.out.println("New en passant move added");
            }
        }
        return schlagen;
    }

    private boolean kannEinEnPassantSeinInZukunft = true;
    private int wieOftBewegt = 0;
    public void setKannEinEnPassantSeinInZukunft(boolean kannEinEnPassantSeinInZukunft) {
        this.kannEinEnPassantSeinInZukunft = kannEinEnPassantSeinInZukunft;
    }
    public boolean kannEinEnPassantSein(){
        return wieOftBewegt == 1 && this.getJetzigesFeld().getZeile() == ((this.getTeam() == Teams.BLACK) ? 3 : 4) && kannEinEnPassantSeinInZukunft;
    }
}
