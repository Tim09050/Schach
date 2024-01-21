package schach.projekt.GameManager;

import schach.projekt.feld.Feld;
import schach.projekt.schachFiguren.figuren.Turm;

public class Rochade {

    public Feld neuesFeldKoenig;
    public Feld neuesFeldTurm;
    public Turm turmZurRochade;

    public Rochade(Feld neuesFeldKoenig, Feld neuesFeldTurm, Turm turmZurRochade) {
        this.neuesFeldKoenig = neuesFeldKoenig;
        this.neuesFeldTurm = neuesFeldTurm;
        this.turmZurRochade = turmZurRochade;
    }
}
