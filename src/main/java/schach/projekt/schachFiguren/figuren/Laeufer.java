package schach.projekt.schachFiguren.figuren;

import schach.projekt.feld.Feld;
import schach.projekt.schachFiguren.Schachfiguren;
import schach.projekt.schachFiguren.Teams;

public class Laeufer extends Schachfiguren {
    public Laeufer(Teams team, Feld startFeld) {
        super("schachfiguren/" + team.name().toLowerCase() + "_bishop.svg", team, startFeld);
    }
}
