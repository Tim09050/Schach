package chess.project.piece;

import chess.project.Board;

import java.awt.image.BufferedImage;

public class Bishop extends Piece{

    public Bishop(Board board, int column, int row, boolean isWhite) {
        super(board);
        this.column = column;
        this.row = row;
        this.xPos = column * board.tileSize;
        this.yPos = row * board.tileSize;

        this.isWhite = isWhite;
        this.name = "Bishop";

        this.sprite = sheet.getSubimage(2 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);
    }

    @Override
    public boolean isValidMovement(int column, int row) {
        return Math.abs(this.column - column) == Math.abs(this.row - row);
    }

    @Override
    public boolean moveCollidesWithPiece(int column, int row) {
        //up-left diagonal
        if(this.column > column && this.row > row){
            for(int i = 1; i < Math.abs(this.column - column); i++){
                if(board.getPiece(this.column - i, this.row - i) != null){
                    return true;
                }
            }
        }
        //up-right diagonal
        if(this.column < column && this.row > row){
            for(int i = 1; i < Math.abs(this.column - column); i++){
                if(board.getPiece(this.column + i, this.row - i) != null){
                    return true;
                }
            }
        }
        //down-left diagonal
        if(this.column > column && this.row < row){
            for(int i = 1; i < Math.abs(this.column - column); i++){
                if(board.getPiece(this.column - i, this.row + i) != null){
                    return true;
                }
            }
        }
        //up-right diagonal
        if(this.column < column && this.row < row){
            for(int i = 1; i < Math.abs(this.column - column); i++){
                if(board.getPiece(this.column + i, this.row + i) != null){
                    return true;
                }
            }
        }

        return false;
    }
}
