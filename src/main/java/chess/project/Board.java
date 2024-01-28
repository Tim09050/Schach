package chess.project;

import chess.project.piece.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel {

    public int tileSize = 75;
    int column = 8;
    int row = 8;
    ArrayList<Piece> pieceList = new ArrayList<>();
    public Piece selectedPiece;
    Input input = new Input(this);

    public Board(){
        this.setPreferredSize(new Dimension(column * tileSize, row * tileSize));
        this.addMouseListener(input);
        this.addMouseMotionListener(input);

        addPieces();
    }

    public Piece getPiece(int column, int row){
        for(Piece piece : pieceList){
            if(piece.column == column && piece.row == row){
                return piece;
            }
        }
        return null;
    }

    public void makeMove(Move move){
        move.piece.column = move.newColumn;
        move.piece.row = move.newRow;

        move.piece.xPos = move.newColumn * tileSize;
        move.piece.yPos = move.newRow * tileSize;

        move.piece.isFirstMove = false;

        capture(move);
    }

    public void capture(Move move){
        pieceList.remove(move.capture);
    }

    public boolean isValidMove(Move move){
        if(sameTeam(move.piece, move.capture)) {
            return false;
        }
        if(!move.piece.isValidMovement(move.newColumn, move.newRow)){
            return false;
        }
        if(move.piece.moveCollidesWithPiece(move.newColumn, move.newRow)){
            return false;
        }

        return true;
    }

    public boolean sameTeam(Piece firstPiece, Piece secondPiece){
        if(firstPiece == null || secondPiece == null) {
            return false;
        }
        return firstPiece.isWhite == secondPiece.isWhite;
    }

    public void addPieces() {

        //Adding pawns
        for(int p = 0; p <= 7; p++){
            pieceList.add(new Pawn(this, p, 1, false));
            pieceList.add(new Pawn(this, p, 6, true));
        }

        //Adding knights
        pieceList.add(new Knight(this, 1, 0, false));
        pieceList.add(new Knight(this, 6, 0, false));
        pieceList.add(new Knight(this, 1, 7, true));
        pieceList.add(new Knight(this, 6, 7, true));

        //Adding bishops
        pieceList.add(new Bishop(this, 2, 0, false));
        pieceList.add(new Bishop(this, 5, 0, false));
        pieceList.add(new Bishop(this, 2, 7, true));
        pieceList.add(new Bishop(this, 5, 7, true));

        //Adding rooks
        pieceList.add(new Rook(this, 0, 0, false));
        pieceList.add(new Rook(this, 7, 0, false));
        pieceList.add(new Rook(this, 0, 7, true));
        pieceList.add(new Rook(this, 7, 7, true));

        //Adding Queens
        pieceList.add(new Queen(this, 3, 0, false));
        pieceList.add(new Queen(this, 3, 7, true));

        //Adding Kings
        pieceList.add(new King(this, 4, 0, false));
        pieceList.add(new King(this, 4, 7, true));
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        Color blue = new Color(71, 117, 171);
        Color white = new Color(214, 221, 229);

        for (int r = 0; r < row; r++){
            for (int c = 0; c < column; c++) {
                graphics2D.setColor((r + c) % 2 == 0 ? white : blue);
                graphics2D.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);
            }
        }
        if(selectedPiece != null) {
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < column; c++) {
                    if (isValidMove(new Move(this, selectedPiece, c, r))) {
                        graphics2D.setColor(new Color(68, 179, 57, 190));
                        graphics2D.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);
                    }
                }
            }
        }


            for(Piece piece : pieceList){
                piece.paint(graphics2D);
            }

    }
}
