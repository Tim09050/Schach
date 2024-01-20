package schach.projekt.schachFiguren.figuren;

import schach.projekt.feld.Feld;
import schach.projekt.schachFiguren.Schachfiguren;
import schach.projekt.schachFiguren.Teams;

public class Koenigin extends Schachfiguren {

    public Koenigin(Teams team, Feld startFeld) {
        super("schachfiguren/" + team.name().toLowerCase() + "_queen.svg", team, startFeld);
    }
}
