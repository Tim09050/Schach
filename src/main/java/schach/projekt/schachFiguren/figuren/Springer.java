package schach.projekt.schachFiguren.figuren;

import schach.projekt.feld.Feld;
import schach.projekt.schachFiguren.Schachfiguren;
import schach.projekt.schachFiguren.Teams;

public class Springer extends Schachfiguren {
    public Springer(Teams team, Feld startFeld) {
        super("schachfiguren/" + team.name().toLowerCase() + "_knight.svg", team, startFeld);
    }
}
