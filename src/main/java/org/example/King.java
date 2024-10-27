package org.example;

public class King extends ChessPiece {
    public King(String color) {
        super(color);
    }

    public String getSymbol() {
        return "K";
    }

    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (toLine < 0 || toLine > 7 || toColumn < 0 || toColumn > 7) return false;
        if (chessBoard.board[toLine][toColumn] != null && chessBoard.board[toLine][toColumn].getSymbol().equals("K"))
            return false;
        if (line - toLine == 0 && column - toColumn == 0) return false;
        if (isUnderAttack(chessBoard, toLine, toColumn)) {
            System.out.println("Ходить под шах нельзя!");
            return false;
        }
        return ((Math.abs(line - toLine) == 0 || Math.abs(line - toLine) == 1) && (Math.abs(column - toColumn) == 0 || Math.abs(column - toColumn) == 1));

    }

    public boolean isUnderAttack(ChessBoard chessBoard, int line, int column) {
        boolean[][] attackMap = new boolean[8][8];

        for (int checkLine = 0; checkLine <= 7; checkLine++) {
            for (int checkColumn = 0; checkColumn <= 7; checkColumn++) {
                if (line == checkLine && column == checkColumn) continue;
                if (chessBoard.board[checkLine][checkColumn] == null) continue;
                if (chessBoard.board[checkLine][checkColumn].getColor().equals(color)) continue;

                ChessPiece cp = chessBoard.board[checkLine][checkColumn];
                switch (cp.getSymbol()) {
                    case "K":
                        setAttackIfInBounds(attackMap, checkLine + 1, checkColumn);
                        setAttackIfInBounds(attackMap, checkLine + 1, checkColumn);
                        setAttackIfInBounds(attackMap, checkLine + 1, checkColumn);
                        setAttackIfInBounds(attackMap, checkLine - 1, checkColumn);
                        setAttackIfInBounds(attackMap, checkLine - 1, checkColumn);
                        setAttackIfInBounds(attackMap, checkLine - 1, checkColumn);

                    case "P":
                        setAttackIfInBounds(attackMap, checkLine + 1, checkColumn);
                        setAttackIfInBounds(attackMap, checkLine - 1, checkColumn);
                    case "H":
                        setAttackIfInBounds(attackMap, checkLine + 2, checkColumn + 1);
                        setAttackIfInBounds(attackMap, checkLine + 2, checkColumn - 1);
                        setAttackIfInBounds(attackMap, checkLine - 2, checkColumn + 1);
                        setAttackIfInBounds(attackMap, checkLine - 2, checkColumn - 1);
                        setAttackIfInBounds(attackMap, checkLine + 1, checkColumn + 2);
                        setAttackIfInBounds(attackMap, checkLine + 1, checkColumn - 2);
                        setAttackIfInBounds(attackMap, checkLine - 1, checkColumn + 2);
                        setAttackIfInBounds(attackMap, checkLine - 1, checkColumn - 2);
                }

                if (cp.getSymbol().equals("R") || cp.getSymbol().equals("Q")) {
                    for (int l = checkLine + 1; l <= 7; l++) {
                        if (chessBoard.board[l][checkColumn] != null) {
                            attackMap[l][checkColumn] = true;
                            break;
                        }
                        attackMap[l][checkColumn] = true;
                    }
                    for (int c = checkColumn + 1; c <= 7; c++) {
                        if (chessBoard.board[checkLine][c] != null) {
                            attackMap[checkLine][c] = true;
                            break;
                        }
                        attackMap[checkLine][c] = true;
                    }
                    for (int l = checkLine - 1; l >= 0; l--) {
                        if (chessBoard.board[l][checkColumn] != null) {
                            attackMap[l][checkColumn] = true;
                            break;
                        }
                        attackMap[l][checkColumn] = true;
                    }
                    for (int c = checkColumn - 1; c >= 0; c--) {
                        if (chessBoard.board[checkLine][c] != null) {
                            attackMap[checkLine][c] = true;
                            break;
                        }
                        attackMap[checkLine][c] = true;
                    }
                }
                if (cp.getSymbol().equals("B") || cp.getSymbol().equals("Q")) {
                    boolean checkUpperRight = true, checkUpperLeft = true, checkBottomRight = true, checkBottomLeft = true;
                    int currentLine, currentColumn;
                    for (int i = 1; i <= 7; i++) {
                        if (checkUpperRight) {
                            currentLine = checkLine + i;
                            currentColumn = checkColumn + i;
                            if (currentLine > 7 || currentColumn > 7) {
                                checkUpperRight = false;
                            } else if (chessBoard.board[currentLine][currentColumn] != null) {
                                attackMap[currentLine][currentColumn] = true;
                                checkUpperRight = false;
                            } else {
                                attackMap[currentLine][currentColumn] = true;
                            }

                        }
                        if (checkUpperLeft) {
                            currentLine = checkLine + i;
                            currentColumn = checkColumn - i;
                            if (currentLine > 7 || currentColumn < 0) {
                                checkUpperLeft = false;
                            } else if (chessBoard.board[currentLine][currentColumn] != null) {
                                attackMap[currentLine][currentColumn] = true;
                                checkUpperLeft = false;
                            } else {
                                attackMap[currentLine][currentColumn] = true;
                            }
                        }
                        if (checkBottomRight) {
                            currentLine = checkLine - i;
                            currentColumn = checkColumn + i;
                            if (currentLine < 0 || currentColumn > 7) {
                                checkBottomRight = false;
                            } else if (chessBoard.board[currentLine][currentColumn] != null) {
                                attackMap[currentLine][currentColumn] = true;
                                checkBottomRight = false;
                            } else {
                                attackMap[currentLine][currentColumn] = true;
                            }
                        }
                        if (checkBottomLeft) {
                            currentLine = checkLine - i;
                            currentColumn = checkColumn - i;
                            if (currentLine < 0 || currentColumn < 0) {
                                checkBottomLeft = false;
                            } else if (chessBoard.board[currentLine][currentColumn] != null) {
                                attackMap[currentLine][currentColumn] = true;
                                checkBottomLeft = false;
                            } else {
                                attackMap[currentLine][currentColumn] = true;
                            }
                        }
                    }
                }


            }
        }
        printBoard(attackMap);
        return attackMap[line][column];
    }

    private void setAttackIfInBounds(boolean[][] attackMap, int attackLine, int attackColumn) {
        if (attackLine < 0 || attackLine > 7 || attackColumn < 0 || attackColumn > 7) {
            return;
        }
        attackMap[attackLine][attackColumn] = true;
    }

    public void printBoard(boolean[][] attackMap) {  //print board in console
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {

                System.out.print(attackMap[i][j] ? "true" + "\t" : "false" + "\t");
            }
            System.out.println();
            System.out.println();
        }
    }
}
