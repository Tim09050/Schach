package schach.projekt.Schachbrett;

import schach.projekt.feld.Feld;
import schach.projekt.schachFiguren.SchachfigurPanel;


import javax.swing.*;
import java.awt.*;


public class Schachbrett implements Runnable {
    private JPanel BrettFenster;
    private static SchachfigurPanel schachfigurPanel;
    public static final int BREITE = 800;
    public static final int HOEHE = 800;
    public static final Feld[] felder = new Feld[64];
    private static Feld ausgewaehltesFeld;


    public JPanel generiereBildschirm(){
        JPanel panel = new JPanel(new GridLayout(8, 8));
        for(int y = 0; y < 8; y++){
            for(int x = 0; x < 8; x++){
                Feld feld = new Feld(x, y);
                felder[8 * y + x] = feld;
                panel.add(feld);
            }

        }
        return panel;
    }


    @Override
    public void run() {
        showGUI();
    }

    public static void setAusgewaehltesFeld(Feld ausgewaehltesFeld) {
        Schachbrett.ausgewaehltesFeld = ausgewaehltesFeld;
    }

    public static Feld getAusgewaehltesFeld() {
        return ausgewaehltesFeld;
    }

    private void showGUI(){
        JFrame frame = new JFrame("SchachBrett");
        frame.setPreferredSize(new Dimension(BREITE, HOEHE));
        JPanel cp = generiereBildschirm();
        schachfigurPanel = new SchachfigurPanel();
        frame.setLayeredPane(schachfigurPanel);
        frame.setContentPane(cp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        schachfigurPanel.repaint();
        frame.setVisible(true);
    }

    public static SchachfigurPanel getSchachfigurPanel() {
        return schachfigurPanel;
    }



    public static void main(String[] args) {
        EventQueue.invokeLater(new Schachbrett());
    }
}
