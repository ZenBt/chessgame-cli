package org.example;


public class Pawn extends ChessPiece {

    public Pawn(String color) {
        super(color);
    }

    public String getSymbol() {
        return "P";
    }

    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {

        if (toLine < 0 || toLine > 7 || toColumn < 0 || toColumn > 7) return false;
        if (chessBoard.board[toLine][toColumn] != null && chessBoard.board[toLine][toColumn].getSymbol().equals("K"))
            return false;
        if (color.equals("White") && line == 1) {
            return ((toLine - line == 2 && column == toColumn) || canMoveToOneForward(line, column, toLine, toColumn) || canMoveDiagonally(chessBoard, line, column, toLine, toColumn));
        } else if (color.equals("Black") && line == 6) {
            return ((toLine - line == -2 && column == toColumn) || canMoveToOneForward(line, column, toLine, toColumn) || canMoveDiagonally(chessBoard, line, column, toLine, toColumn));
        }
        return canMoveToOneForward(line, column, toLine, toColumn) || canMoveDiagonally(chessBoard, line, column, toLine, toColumn);
    }

    private boolean canMoveToOneForward(int line, int column, int toLine, int toColumn) {
        if (color.equals("White")) {
            return (toLine - line == 1 && column == toColumn);
        } else {
            return (toLine - line == -1 && column == toColumn);
        }

    }

    private boolean canMoveDiagonally(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (color.equals("White")) {
            return (toLine - line == 1 && Math.abs(column - toColumn) == 1 && chessBoard.board[toLine][toColumn] != null);
        } else {
            return (toLine - line == -1 && Math.abs(column - toColumn) == 1 && chessBoard.board[toLine][toColumn] != null);
        }
    }
}
