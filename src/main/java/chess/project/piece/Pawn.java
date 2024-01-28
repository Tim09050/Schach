package chess.project.piece;

import chess.project.Board;

import java.awt.image.BufferedImage;

public class Pawn extends Piece{

    public Pawn(Board board, int column, int row, boolean isWhite) {
        super(board);
        this.column = column;
        this.row = row;
        this.xPos = column * board.tileSize;
        this.yPos = row * board.tileSize;

        this.isWhite = isWhite;
        this.name = "Pawn";

        this.sprite = sheet.getSubimage(5 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);
    }

    @Override
    public boolean isValidMovement(int column, int row) {
        int colorIndex = isWhite ? 1 : -1;

        //moving one forward
        if(this.column == column &&  row == this.row - colorIndex && board.getPiece(column, row) == null) {
            return true;
        }
        //moving two forward
        if(isFirstMove && this.column == column &&  row == this.row - colorIndex * 2 && board.getPiece(column, row) == null && board.getPiece(column, row + colorIndex) == null) {
            return true;
        }
        //capture left
        if(column == this.column - 1 && row == this.row -colorIndex && board.getPiece(column, row) != null) {
            return true;
        }
        //capture right
        if(column == this.column + 1 && row == this.row -colorIndex && board.getPiece(column, row) != null) {
            return true;
        }
        //en passant
        if(board.getTileNumber(column, row) == board.enPassantTile && column == this.column - 1 && row == this.row - colorIndex && board.getPiece(column, row + colorIndex) != null) {
            return true;
        }
        if(board.getTileNumber(column, row) == board.enPassantTile && column == this.column + 1 && row == this.row - colorIndex && board.getPiece(column, row + colorIndex) != null) {
            return true;
        }
        return false;
    }
}
