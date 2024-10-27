package org.example;

public class Queen extends ChessPiece {
    public Queen(String color) {
        super(color);
    }

    public String getSymbol() {
        return "Q";
    }

    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (toLine < 0 || toLine > 7 || toColumn < 0 || toColumn > 7) return false;
        if (chessBoard.board[toLine][toColumn] != null && chessBoard.board[toLine][toColumn].getSymbol().equals("K"))
            return false;
        if (canMoveOnDiagonal(chessBoard, line, column, toLine, toColumn)) return true;

        return canMoveOnLine(chessBoard, line, column, toLine, toColumn);

    }

    private boolean canMoveOnDiagonal(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
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

    private boolean canMoveOnLine(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
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
