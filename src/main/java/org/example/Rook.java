package org.example;

public class Rook extends ChessPiece {
    public Rook(String color) {
        super(color);
    }

    public String getSymbol() {
        return "R";
    }

    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (toLine < 0 || toLine > 7 || toColumn < 0 || toColumn > 7) return false;
        if (chessBoard.board[toLine][toColumn] != null && chessBoard.board[toLine][toColumn].getSymbol().equals("K"))
            return false;
        if (line == toLine && column != toColumn) {
            for (int i = Math.min(column, toColumn) + 1; i < Math.max(column, toColumn); i++) {
                if (chessBoard.board[line][i] != null) {
                    System.out.println("На вашем пути есть фигура, милорд!");
                    return false;
                }
            }
        } else if (line != toLine && column == toColumn) {
            for (int j = Math.min(line, toLine) + 1; j < Math.max(line, toLine); j++) {
                if (chessBoard.board[j][column] != null) {
                    System.out.println("На вашем пути есть фигура, милорд!");
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }
}
