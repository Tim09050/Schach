package schach.projekt;

import schach.projekt.feld.Feld;

import javax.swing.*;
import java.awt.*;

public class Schachbrett implements Runnable {
    private JPanel BrettFenster;
    public static final int breite = 800;
    public static final int länge = 800;


    public JPanel generiereBildschirm(){
        JPanel panel = new JPanel(new GridLayout(8, 8));
        for(int i = 0; i < 64; i++){
            Feld feld = new Feld(i);
            panel.add(feld);
        }
        return panel;
    }


    @Override
    public void run() {
        showGUI();
    }

    private void showGUI(){
        JFrame frame = new JFrame("SchachBrett");
        frame.setPreferredSize(new Dimension(breite, länge));
        frame.setContentPane(generiereBildschirm());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Schachbrett());
    }
}
