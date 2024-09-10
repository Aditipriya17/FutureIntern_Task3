import java.util.Scanner;

public class TicTacToe {

    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            initializeBoard();
            playGame(scanner);
            System.out.println("Do you want to play again? (y/n)");
            char response = scanner.next().toLowerCase().charAt(0);
            playAgain = (response == 'y');
        }

        System.out.println("Thanks for playing!");
    }

    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
        currentPlayer = 'X';
    }

    private static void displayBoard() {
        System.out.println("Board:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void playGame(Scanner scanner) {
        boolean gameWon = false;
        boolean isDraw = false;

        while (!gameWon && !isDraw) {
            displayBoard();
            playerMove(scanner);
            gameWon = checkForWin();
            if (!gameWon) {
                isDraw = checkForDraw();
                if (!isDraw) {
                    switchPlayer();
                }
            }
        }

        displayBoard();

        if (gameWon) {
            System.out.println("Player " + currentPlayer + " wins!");
        } else if (isDraw) {
            System.out.println("It's a draw!");
        }
    }

    private static void playerMove(Scanner scanner) {
        boolean validMove = false;

        while (!validMove) {
            System.out.println("Player " + currentPlayer + ", enter your move (row and column: 1 2): ");
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;

            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-') {
                board[row][col] = currentPlayer;
                validMove = true;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }

    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private static boolean checkForWin() {
        return (checkRows() || checkColumns() || checkDiagonals());
    }

    private static boolean checkForDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkColumns() {
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkDiagonals() {
        return (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
               (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer);
    }
}
