package chess.project.piece;

import chess.project.Board;

import java.awt.image.BufferedImage;

public class King extends Piece{

    public King(Board board, int column, int row, boolean isWhite) {
        super(board);
        this.column = column;
        this.row = row;
        this.xPos = column * board.tileSize;
        this.yPos = row * board.tileSize;

        this.isWhite = isWhite;
        this.name = "Knight";

        this.sprite = sheet.getSubimage(0, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);
    }
}