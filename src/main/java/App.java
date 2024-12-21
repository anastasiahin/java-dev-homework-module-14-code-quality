import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}

class Game {
    private final Board board;
    private final Player player;
    private final ComputerPlayer computerPlayer;

    public Game() {
        this.board = new Board();
        this.player = new Player('X');
        this.computerPlayer = new ComputerPlayer('O');
    }

    public void start() {
        board.display();
        while (true) {
            player.makeMove(board);
            if (board.checkWinner(player.getSymbol())) {
                System.out.println("You won!");
                break;
            }
            if (board.isFull()) {
                System.out.println("It's a draw!");
                break;
            }

            computerPlayer.makeMove(board);
            if (board.checkWinner(computerPlayer.getSymbol())) {
                System.out.println("You lost!");
                break;
            }
            if (board.isFull()) {
                System.out.println("It's a draw!");
                break;
            }
        }
    }
}

class Board {
    private final char[] cells = new char[9];

    public Board() {
        for (int i = 0; i < cells.length; i++) {
            cells[i] = (char) ('1' + i);
        }
    }

    public void display() {
        System.out.println("\n " + cells[0] + " | " + cells[1] + " | " + cells[2]);
        System.out.println("-----------");
        System.out.println(" " + cells[3] + " | " + cells[4] + " | " + cells[5]);
        System.out.println("-----------");
        System.out.println(" " + cells[6] + " | " + cells[7] + " | " + cells[8] + "\n");
    }

    public boolean makeMove(int position, char symbol) {
        if (position < 1 || position > 9 || cells[position - 1] == 'X' || cells[position - 1] == 'O') {
            return false;
        }
        cells[position - 1] = symbol;
        return true;
    }

    public boolean checkWinner(char symbol) {
        int[][] winConditions = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns
                {0, 4, 8}, {2, 4, 6}             // Diagonals
        };

        for (int[] condition : winConditions) {
            if (cells[condition[0]] == symbol && cells[condition[1]] == symbol && cells[condition[2]] == symbol) {
                return true;
            }
        }
        return false;
    }

    public boolean isFull() {
        for (char cell : cells) {
            if (cell != 'X' && cell != 'O') {
                return false;
            }
        }
        return true;
    }
}

class Player {
    private final char symbol;

    public Player(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public void makeMove(Board board) {
        Scanner scanner = new Scanner(System.in);
        int position;
        while (true) {
            System.out.println("Enter your move (1-9):");
            position = scanner.nextInt();
            if (board.makeMove(position, symbol)) {
                break;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
        board.display();
    }
}

class ComputerPlayer {
    private final char symbol;

    public ComputerPlayer(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public void makeMove(Board board) {
        int position;
        while (true) {
            position = (int) (Math.random() * 9 + 1);
            if (board.makeMove(position, symbol)) {
                break;
            }
        }
        System.out.println("Computer made its move.");
        board.display();
    }
}
