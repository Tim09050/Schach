package schach.projekt.GameManager;

import schach.projekt.feld.Feld;

public class EnPassant {

    public Feld feldEntfernen;
    public Feld zielFeld;

    public EnPassant(Feld feldEntfernen, Feld zielFeld) {
        this.feldEntfernen = feldEntfernen;
        this.zielFeld = zielFeld;
    }
}
