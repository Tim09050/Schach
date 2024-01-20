package schach.projekt.schachFiguren.figuren;

import schach.projekt.feld.Feld;
import schach.projekt.schachFiguren.Schachfiguren;
import schach.projekt.schachFiguren.Teams;

public class Bauer extends Schachfiguren {
    public Bauer(Teams team, Feld startFeld) {
        super("schachfiguren/" + team.name().toLowerCase() + "_pawn.svg", team, startFeld);
    }
}
