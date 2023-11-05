import java.sql.Struct;
import java.util.Scanner;
public class TicTacToe {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String player = "X";
        int moveCount = 0;
        int row = -1;
        int col = -1;
        boolean playing = true;
        boolean done = false;
        final int MOVES_FOR_WIN = 5;
        final int MOVES_FOR_TIE = 7;


        //start game

        do {
            clearBoard();//clear board method
            player = "X";
            moveCount = 0;
            playing = true;
            done = false;



            do {


                do {
                    showBoard();
                    System.out.println("Enter move for " + player);
                    row = SafeInput.getRangedInt(scan, "Enter coordinates for row ", 1, 3);
                    col = SafeInput.getRangedInt(scan, "Enter coordinates for col ", 1, 3);
                    row--; // board is 0-2
                    col--;


                } while (!isValidMove(row, col));
                board[row][col] = player; // putting the play on the board
                moveCount++;

                if(moveCount >= MOVES_FOR_WIN)
                {
                    if(isWin(player))
                    {
                        showBoard();
                        System.out.println("Player " + player + " won!");
                        playing = false;
                    }
                }
                if(moveCount >= MOVES_FOR_TIE)
                {
                    if(isTie(player))
                    {
                        showBoard();
                        System.out.println("It's a tie!");
                        playing = false;
                    }
                }
                if (player.equals("X")) {
                    player = "O";
                } else {
                    player = "X";
                }


            } while (playing);
            done= SafeInput.getYNConfirm(scan,"Are you done playing?");
        } while (!done);


    }// end of main

    private static void clearBoard() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                board[row][col] = " ";
            }
        }
    }

    private static void showBoard() {
        System.out.println("| " + board[0][0] + " | " + board[0][1] + " | " + board[0][2] + " |");
        System.out.println("| " + board[1][0] + " | " + board[1][1] + " | " + board[1][2] + " |");
        System.out.println("| " + board[2][0] + " | " + board[2][1] + " | " + board[2][2] + " |");
    }


    private static boolean isValidMove(int row, int col) {
        boolean retVal = false;
        if (board[row][col].equals(" "))
            retVal = true;
        return retVal;
    }

    private static boolean isWin(String player) {
        if (isColWin(player) || isRowWin(player) || isDiagonalWin(player)) {
            return true;
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int col = 0; col < COL; col++) {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
                return true;
            }
        }
        return false; // no col win
    }

    public static boolean isRowWin(String player) {
        for (int row = 0; row < ROW; row++) {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) {
                return true;
            }
        }
        return false; // no row win
    }

    private static boolean isDiagonalWin(String player) {
        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) {
            return true;
        } else if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)) {
            return true;
        } else {
            return false; // no diagonal win
        }
    }

    private static boolean isTie(String player) {
        if (isRowTie(player) || isColTie(player) || isDiagonalTie(player)) {
            return true;
        }
        return false;
    }

    private static boolean isRowTie(String player) {
        //runs loop to check for x and o in same row

        boolean rowOneX = false;
        boolean rowOneO = false;

        for (int col = 0; col < COL; col++) {
            if (board[0][col].equals("X")) {
                rowOneX = true;
            }
            if (board[0][col].equals("O")) {
                rowOneO = true;
            }

        }
        for (int col = 0; col < COL; col++) {
            if (board[1][col].equals("X")) {
                rowOneX = true;
            }
            if (board[1][col].equals("O")) {
                rowOneO = true;
            }

        }

        for (int col = 0; col < COL; col++) {
            if (board[2][col].equals("X")) {
                rowOneX = true;
            }
            if (board[2][col].equals("O")) {
                rowOneO = true;
            }

        }

        return rowOneX && rowOneO;
    }


    private static boolean isDiagonalTie(String player) {
        boolean diagonalX = false;
        boolean diagonalO = false;
        if (board[0][2].equals("X") && board[1][1].equals("X") && board[2][0].equals("X")) {
            diagonalX = true;
        }
        if (board[0][2].equals("O") && board[1][1].equals("O") && board[2][0].equals("O")) {
            diagonalO = true;
        }
        if (board[0][0].equals("X") && board[1][1].equals("X") && board[2][2].equals("X")) {
            diagonalX = true;
        }
        if (board[0][0].equals("O") && board[1][1].equals("O") && board[2][2].equals("O")) {
            diagonalO = true;
        }
        return !(diagonalX && diagonalO);
    }





    private static boolean isColTie(String player) {


        boolean colOneX = false;
        boolean colOneO = false;

        for (int row = 0; row < ROW; row++) {
            if (board[0][row].equals("X")) {
                colOneX = true;
            }
            if (board[0][row].equals("O")) {
                colOneO = true;
            }

        }
        for (int row = 0; row < ROW; row++) {
            if (board[1][row].equals("X")) {
                colOneX = true;
            }
            if (board[1][row].equals("O")) {
                colOneO = true;
            }

        }

        for (int row = 0; row < ROW; row++) {
            if (board[2][row].equals("X")) {
                colOneX = true;
            }
            if (board[2][row].equals("O")) {
                colOneO = true;
            }

        }

        return colOneX && colOneO;
    }
}









