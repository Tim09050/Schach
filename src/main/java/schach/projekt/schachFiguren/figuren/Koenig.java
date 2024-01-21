package schach.projekt.schachFiguren.figuren;

import schach.projekt.GameManager.Rochade;
import schach.projekt.Schachbrett.Schachbrett;
import schach.projekt.feld.Feld;
import schach.projekt.schachFiguren.Schachfiguren;
import schach.projekt.schachFiguren.Teams;
import schach.projekt.GameManager.GameManager;

import java.util.ArrayList;

public class Koenig extends Schachfiguren {


    public Koenig(Teams team, Feld startFeld) {
        super("schachfiguren/" + team.name().toLowerCase() + "_king.svg", team, startFeld);
    }

    public ArrayList<Feld> getGueltigenZug() {
        Integer[] vektoren = getVektoren();
        ArrayList<Feld> gueltigerZug = new ArrayList<>();
        int jetzigesFeldIndex = this.getJetzigesFeld().getFeldId();
        for (Integer vektor : vektoren) {
            int neuerFeldIndex = jetzigesFeldIndex + vektor;
            if (neuerFeldIndex < 0 || neuerFeldIndex > 63) continue;
            if (Schachbrett.felder[neuerFeldIndex].getFigurAufFeld() != null && Schachbrett.felder[neuerFeldIndex].getFigurAufFeld().getTeam() == this.getTeam())
                continue;
            gueltigerZug.add(Schachbrett.felder[neuerFeldIndex]);
        }
        gueltigerZug.addAll(getRochade());
        return gueltigerZug;
    }

    public ArrayList<Feld> getRochade(){
        ArrayList<Feld> rochade = new ArrayList<>();
        //kurze Rochade
        try {
            Turm turm = ((Turm) Schachbrett.felder[this.getJetzigesFeld().getFeldId() + 3].getFigurAufFeld());
            if(Schachbrett.felder[this.getJetzigesFeld().getFeldId() + 1].getFigurAufFeld() != null || Schachbrett.felder[this.getJetzigesFeld().getFeldId() + 2].getFigurAufFeld() != null) throw new Exception();
            Feld neueKoenigPos = Schachbrett.felder[this.getJetzigesFeld().getFeldId() + 2];
            Feld neueTurmPos = Schachbrett.felder[neueKoenigPos.getFeldId() - 1];
            rochade.add(neueKoenigPos);
            GameManager.addRochade(new Rochade(neueKoenigPos, neueTurmPos, turm));
        }catch (Exception ignored){}
        //lange Rochade
        try {
            Turm turm = ((Turm) Schachbrett.felder[this.getJetzigesFeld().getFeldId() - 4].getFigurAufFeld());
            if(Schachbrett.felder[this.getJetzigesFeld().getFeldId() - 1].getFigurAufFeld() != null || Schachbrett.felder[this.getJetzigesFeld().getFeldId() - 2].getFigurAufFeld() != null || Schachbrett.felder[this.getJetzigesFeld().getFeldId() - 3].getFigurAufFeld() != null) throw new Exception();
            Feld neueKoenigPos = Schachbrett.felder[this.getJetzigesFeld().getFeldId() - 2];
            Feld neueTurmPos = Schachbrett.felder[neueKoenigPos.getFeldId() + 1];
            rochade.add(neueKoenigPos);
            GameManager.addRochade(new Rochade(neueKoenigPos, neueTurmPos, turm));
        }catch (Exception ignored){}

        return rochade;
    }


    private Integer[] getVektoren() {
        Feld jetzigesFeld = this.getJetzigesFeld();
        if (jetzigesFeld.getSpalte() == 7) {
            return new Integer[]{-1, -8, 8, -9, 7};
        }else if (jetzigesFeld.getSpalte() == 0) {
            return new Integer[]{1, -8, 8, -7, 9};
        }
        return new Integer[]{-1, 1, -8, 8, -9, -7, 9, 7};
    }
}
