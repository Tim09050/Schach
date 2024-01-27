package chess.project;

import chess.project.piece.Piece;


public class Move {

    int oldColumn;
    int oldRow;
    int newColumn;
    int newRow;
    Piece piece;
    Piece capture;

    public Move(Board board, Piece piece, int newColumn, int newRow){
        this.oldColumn = piece.column;
        this.oldRow = piece.row;
        this.newColumn = newColumn;
        this.newRow = newRow;

        this.piece = piece;
        this.capture = board.getPiece(newColumn, newRow);
    }
}
