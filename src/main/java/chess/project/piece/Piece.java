package chess.project.piece;

import chess.project.Board;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Piece {

    public int column, row, value, xPos, yPos;
    public boolean isWhite;
    public String name;
    BufferedImage sheet;
    {
        try{
            sheet = ImageIO.read(ClassLoader.getSystemResourceAsStream("pieces.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected int sheetScale = sheet.getWidth()/6;
    Image sprite;
    Board board;

    public Piece(Board board){
        this.board = board;
    }

    public void paint(Graphics2D graphics2D){
        graphics2D.drawImage(sprite, xPos, yPos, null);
    }

}
