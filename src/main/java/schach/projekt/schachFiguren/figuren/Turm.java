package schach.projekt.schachFiguren.figuren;

import schach.projekt.feld.Feld;
import schach.projekt.schachFiguren.Schachfiguren;
import schach.projekt.schachFiguren.Teams;

public class Turm extends Schachfiguren {
    public Turm(Teams team, Feld startFeld) {
        super("schachfiguren/" + team.name().toLowerCase() + "_rook.svg", team, startFeld);
    }
}
