package org.example;

public class Bishop extends ChessPiece {
    public Bishop(String color) {
        super(color);
    }

    public String getSymbol() {
        return "B";
    }

    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (toLine < 0 || toLine > 7 || toColumn < 0 || toColumn > 7) return false;
        if (chessBoard.board[toLine][toColumn] != null && chessBoard.board[toLine][toColumn].getSymbol().equals("K"))
            return false;
        if (Math.abs(line - toLine) != Math.abs(column - toColumn)) return false;
        int minLine, maxLine, minColumn, maxColumn;
        minLine = Math.min(line, toLine) + 1;
        minColumn = Math.min(column, toColumn) + 1;
        maxLine = Math.max(line, toLine) - 1;
        maxColumn = Math.max(column, toColumn) - 1;

        for (int i = minLine; i <= maxLine; i++) {
            for (int j = minColumn; j <= maxColumn; j++) {
                if ((Math.abs(line - i) == Math.abs(column - j)) && chessBoard.board[i][j] != null) {
                    System.out.println("На вашем пути есть фигура, милорд!");
                    return false;
                }
            }
        }
        return true;
    }
}
