package schach.projekt.schachFiguren.figuren;

import schach.projekt.Schachbrett.Schachbrett;
import schach.projekt.feld.Feld;
import schach.projekt.schachFiguren.Schachfiguren;
import schach.projekt.schachFiguren.Teams;

import java.util.ArrayList;

public class Turm extends Schachfiguren {
    public Turm(Teams team, Feld startFeld) {
        super("schachfiguren/" + team.name().toLowerCase() + "_rook.svg", team, startFeld);
    }

    @Override
    public ArrayList<Feld> getGueltigenZug() {
        Integer[] vektoren = getVektoren();
        int jetzigesFeldIndex = this.getJetzigesFeld().getFeldId();
        int startfeldIndex = jetzigesFeldIndex;
        ArrayList<Feld> gueltigerZug = new ArrayList<>();

        for(Integer vektor : vektoren){
            while(true){
                jetzigesFeldIndex += vektor;
                if(jetzigesFeldIndex < 0 || jetzigesFeldIndex > 63) break;
                if(Schachbrett.felder[jetzigesFeldIndex].getFigurAufFeld() != null && Schachbrett.felder[jetzigesFeldIndex].getFigurAufFeld().getTeam() == this.getTeam()) break;
                if((Schachbrett.felder[jetzigesFeldIndex].getFigurAufFeld() != null && Schachbrett.felder[jetzigesFeldIndex].getFigurAufFeld().getTeam() != this.getTeam()) || (((jetzigesFeldIndex) % 8 == 0) || (jetzigesFeldIndex + 1) % 8 == 0) && Math.abs(vektor) == 1){
                    gueltigerZug.add(Schachbrett.felder[jetzigesFeldIndex]);
                    break;
                }
                gueltigerZug.add(Schachbrett.felder[jetzigesFeldIndex]);
            }
            jetzigesFeldIndex = startfeldIndex;
        }
        return gueltigerZug;
    }


    private Integer[] getVektoren(){
        Feld jetzigesFeld = this.getJetzigesFeld();
        if(jetzigesFeld.getSpalte() == 0){
            return new Integer[]{8, -8, 1};
        }else if(jetzigesFeld.getSpalte() == 7){
            return new Integer[]{8, -8, -1};
        }
        return new Integer[]{8, -8, 1, -1};
    }
}
