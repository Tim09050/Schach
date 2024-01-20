package schach.projekt.schachFiguren.figuren;

import schach.projekt.feld.Feld;
import schach.projekt.schachFiguren.Schachfiguren;
import schach.projekt.schachFiguren.Teams;

public class Koenig extends Schachfiguren {


    public Koenig(Teams team, Feld startFeld) {
        super("schachfiguren/" + team.name().toLowerCase() + "_king.svg", team, startFeld);
    }
}
