package chess.project.piece;

import chess.project.Board;

import java.awt.image.BufferedImage;

public class Queen extends Piece{

    public Queen(Board board, int column, int row, boolean isWhite) {
        super(board);
        this.column = column;
        this.row = row;
        this.xPos = column * board.tileSize;
        this.yPos = row * board.tileSize;

        this.isWhite = isWhite;
        this.name = "Queen";

        this.sprite = sheet.getSubimage(sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);
    }

    @Override
    public boolean isValidMovement(int column, int row) {
        return this.column == column || this.row == row || Math.abs(this.column - column) == Math.abs(this.row - row);
    }

    @Override
    public boolean moveCollidesWithPiece(int column, int row) {
        if(this.column == column || this.row == row) {
            //left
            if (this.column > column) {
                for (int c = this.column - 1; c > column; c--) {
                    if (board.getPiece(c, this.row) != null) {
                        return true;
                    }
                }
            }
            //right
            if (this.column < column) {
                for (int c = this.column + 1; c < column; c++) {
                    if (board.getPiece(c, this.row) != null) {
                        return true;
                    }
                }
            }
            //up
            if (this.row > row) {
                for (int r = this.row - 1; r > row; r--) {
                    if (board.getPiece(this.column, r) != null) {
                        return true;
                    }
                }
            }
            //down
            if (this.row < row) {
                for (int r = this.row + 1; r < row; r++) {
                    if (board.getPiece(this.column, r) != null) {
                        return true;
                    }
                }
            }
        } else {
            //up-left diagonal
            if (this.column > column && this.row > row) {
                for (int i = 1; i < Math.abs(this.column - column); i++) {
                    if (board.getPiece(this.column - i, this.row - i) != null) {
                        return true;
                    }
                }
            }
            //up-right diagonal
            if (this.column < column && this.row > row) {
                for (int i = 1; i < Math.abs(this.column - column); i++) {
                    if (board.getPiece(this.column + i, this.row - i) != null) {
                        return true;
                    }
                }
            }
            //down-left diagonal
            if (this.column > column && this.row < row) {
                for (int i = 1; i < Math.abs(this.column - column); i++) {
                    if (board.getPiece(this.column - i, this.row + i) != null) {
                        return true;
                    }
                }
            }
            //up-right diagonal
            if (this.column < column && this.row < row) {
                for (int i = 1; i < Math.abs(this.column - column); i++) {
                    if (board.getPiece(this.column + i, this.row + i) != null) {
                        return true;
                    }
                }
            }
        }



        return false;
    }
}
