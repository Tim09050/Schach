package schach.projekt.schachFiguren.figuren;

import schach.projekt.Schachbrett.Schachbrett;
import schach.projekt.feld.Feld;
import schach.projekt.schachFiguren.Schachfiguren;
import schach.projekt.schachFiguren.Teams;

import java.util.ArrayList;

public class Springer extends Schachfiguren {
    public Springer(Teams team, Feld startFeld) {
        super("schachfiguren/" + team.name().toLowerCase() + "_knight.svg", team, startFeld);
    }

    @Override
    public ArrayList<Feld> getGueltigenZug() {
        Integer[] vektoren = getVektoren();
        ArrayList<Feld> gueltigerZug = new ArrayList<>();
        int jetzigesFeldIndex = this.getJetzigesFeld().getFeldId();
        for (Integer vektor : vektoren) {
            int neuerFeldIndex = jetzigesFeldIndex + vektor;
            if (neuerFeldIndex < 0 || neuerFeldIndex > 63) continue;
            if (Schachbrett.felder[neuerFeldIndex].getFigurAufFeld() != null && Schachbrett.felder[neuerFeldIndex].getFigurAufFeld().getTeam() == this.getTeam())
                continue;
            if ((Schachbrett.felder[neuerFeldIndex].getFigurAufFeld() != null && Schachbrett.felder[neuerFeldIndex].getFigurAufFeld().getTeam() != this.getTeam())) {
                gueltigerZug.add(Schachbrett.felder[neuerFeldIndex]);
                continue;
            }
            gueltigerZug.add(Schachbrett.felder[neuerFeldIndex]);
        }
        return gueltigerZug;
    }


    private Integer[] getVektoren(){
        Feld jetzigesFeld =this.getJetzigesFeld();
        if(jetzigesFeld.getSpalte() == 0){
            return new Integer[]{-15, -6, 17, 10};
        }else if(jetzigesFeld.getSpalte() == 1){
            return new Integer[]{-15, -6, 17, 10, 15, -17};
        }else if(jetzigesFeld.getSpalte() == 6){
            return new Integer[]{15, 6, -17, -10, -15, 17};
        }else if (jetzigesFeld.getSpalte() == 7) {
            return new Integer[]{15, 6, -17, -10};
        }

        return new Integer[]{-15, -17, 15, 17, -6, 10, 6, -10};
    }
}

