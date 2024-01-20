package schach.projekt.schachFiguren.figuren;

import schach.projekt.schachFiguren.Schachfiguren;
import schach.projekt.schachFiguren.Teams;

public class Koenig extends Schachfiguren {


    public Koenig(Teams team) {
        super("schachfiguren/" + team.name().toLowerCase() + "_king.svg", team);
    }
}
