import java.util.Scanner;

public class App {

    private static final byte TOTAL_CELLS = 9;
    private static final char EMPTY_CELL = ' ';
    private static final char USER_MARK = 'X';
    private static final char COMPUTER_MARK = 'O';

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        byte userMove;
        byte computerMove;
        byte winner = 0;
        char[] board = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };

        System.out.println("Enter box number to select. Enjoy!\n");

        while (true) {
            printBoard(board);
            
            if (winner != 0) {
                printResult(winner);
                break;
            }

            userMove = getUserMove(scan, board);
            board[userMove - 1] = USER_MARK;

            if (checkWinner(board, USER_MARK)) {
                winner = 1;
                continue;
            }

            if (isBoardFull(board)) {
                winner = 3;
                continue;
            }

            computerMove = getComputerMove(board);
            board[computerMove - 1] = COMPUTER_MARK;

            if (checkWinner(board, COMPUTER_MARK)) {
                winner = 2;
                continue;
            }
        }
    }

    public static void printBoard(char[] board) {
        System.out.println("\n\n " + board[0] + " | " + board[1] + " | " + board[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8] + " \n");
    }

    public static void printResult(int winner) {
        if (winner == 1) {
            System.out.println("You won the game!");
        } else if (winner == 2) {
            System.out.println("You lost the game!");
        } else {
            System.out.println("It's a draw!");
        }
        System.out.println("Thanks for playing!");
    }

    public static byte getUserMove(Scanner scan, char[] board) {
        byte move;
        while (true) {
            move = scan.nextByte();
            if (move > 0 && move < 10 && board[move - 1] != USER_MARK && board[move - 1] != COMPUTER_MARK) {
                break;
            }
            System.out.println("Invalid move. Enter again.");
        }
        return move;
    }

    public static byte getComputerMove(char[] board) {
        byte move;
        while (true) {
            move = (byte) (Math.random() * TOTAL_CELLS + 1);
            if (board[move - 1] != USER_MARK && board[move - 1] != COMPUTER_MARK) {
                break;
            }
        }
        return move;
    }

    public static boolean checkWinner(char[] board, char mark) {
        return (board[0] == mark && board[1] == mark && board[2] == mark) ||
               (board[3] == mark && board[4] == mark && board[5] == mark) ||
               (board[6] == mark && board[7] == mark && board[8] == mark) ||
               (board[0] == mark && board[3] == mark && board[6] == mark) ||
               (board[1] == mark && board[4] == mark && board[7] == mark) ||
               (board[2] == mark && board[5] == mark && board[8] == mark) ||
               (board[0] == mark && board[4] == mark && board[8] == mark) ||
               (board[2] == mark && board[4] == mark && board[6] == mark);
    }

    public static boolean isBoardFull(char[] board) {
        for (char cell : board) {
            if (cell != USER_MARK && cell != COMPUTER_MARK) {
                return false;
            }
        }
        return true;
    }
}
