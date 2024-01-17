package schach.projekt.schachFiguren;



import schach.projekt.Schachbrett;

import javax.swing.*;
import java.awt.*;

import static schach.projekt.Schachbrett.*;

public class SchachfigurenPanel extends JLayeredPane {

    public SchachfigurenPanel(){
        this.setPreferredSize(new Dimension(Schachbrett.breite,Schachbrett.länge));
    }

    private void initSchachbrett(){

    }
}
