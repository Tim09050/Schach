package chess.project;

import javax.swing.*;
import java.awt.*;

public class StartChess {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.getContentPane().setBackground(Color.BLACK);
        jFrame.setLayout(new GridBagLayout());
        jFrame.setMinimumSize(new Dimension(800, 800));
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Board board = new Board();
        jFrame.add(board);

        jFrame.setVisible(true);
    }
}
