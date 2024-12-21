import java.util.Scanner;
import java.util.Random;

class Board {
    private char[] cells = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public void display() {
        System.out.println("\n\n " + cells[0] + " | " + cells[1] + " | " + cells[2]);
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

    public boolean isFull() {
        for (char cell : cells) {
            if (cell != 'X' && cell != 'O') {
                return false;
            }
        }
        return true;
    }

    public boolean checkWin(char symbol) {
        return (cells[0] == symbol && cells[1] == symbol && cells[2] == symbol) ||
               (cells[3] == symbol && cells[4] == symbol && cells[5] == symbol) ||
               (cells[6] == symbol && cells[7] == symbol && cells[8] == symbol) ||
               (cells[0] == symbol && cells[3] == symbol && cells[6] == symbol) ||
               (cells[1] == symbol && cells[4] == symbol && cells[7] == symbol) ||
               (cells[2] == symbol && cells[5] == symbol && cells[8] == symbol) ||
               (cells[0] == symbol && cells[4] == symbol && cells[8] == symbol) ||
               (cells[2] == symbol && cells[4] == symbol && cells[6] == symbol);
    }
}

class Player {
    private char symbol;

    public Player(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getMove(Scanner scanner) {
        System.out.println("Enter your move (1-9):");
        return scanner.nextInt();
    }
}

class ComputerPlayer extends Player {
    private Random random = new Random();

    public ComputerPlayer(char symbol) {
        super(symbol);
    }

    public int getMove() {
        return random.nextInt(9) + 1;
    }
}

class Game {
    private Board board;
    private Player player;
    private ComputerPlayer computer;

    public Game() {
        board = new Board();
        player = new Player('X');
        computer = new ComputerPlayer('O');
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            board.display();

            // Player move
            boolean validMove;
            do {
                int playerMove = player.getMove(scanner);
                validMove = board.makeMove(playerMove, player.getSymbol());
                if (!validMove) {
                    System.out.println("Invalid move. Try again.");
                }
            } while (!validMove);

            if (board.checkWin(player.getSymbol())) {
                board.display();
                System.out.println("You won!");
                break;
            }

            if (board.isFull()) {
                board.display();
                System.out.println("It's a draw!");
                break;
            }

            // Computer move
            do {
                int computerMove = computer.getMove();
                validMove = board.makeMove(computerMove, computer.getSymbol());
            } while (!validMove);

            if (board.checkWin(computer.getSymbol())) {
                board.display();
                System.out.println("You lost!");
                break;
            }

            if (board.isFull()) {
                board.display();
                System.out.println("It's a draw!");
                break;
            }
        }
        scanner.close();
    }
}

public class App {
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
