package LW_3;

import java.util.Scanner;

public class Main {
 
    public static void main(String[] args) {
        GameField gameField = new GameField();
        Player player1 = new Player("Player 1", 'X');
        Player player2 = new Player("Player 2", 'O');
        Scanner scanner = new Scanner(System.in);

        player1.displayInfo();
        player2.displayInfo();

        boolean player1Turn = true;
        int moves = 0;
        boolean gameWon = false;

        while (moves < 9 && !gameWon) {
            System.out.println("Current Board:");
            gameField.printBoard();

            Player currentPlayer = player1Turn ? player1 : player2;

            System.out.println(currentPlayer.getName() + "'s turn (" + currentPlayer.getSymbol() + ")");
            int row, col;

            do {
                System.out.print("Enter row (0-2): ");
                row = scanner.nextInt();
                System.out.print("Enter column (0-2): ");
                col = scanner.nextInt();
            } while (row < 0 || row > 2 || col < 0 || col > 2);

            if (gameField.makeMove(row, col, currentPlayer.getSymbol())) {
                moves++;
                gameWon = gameField.checkWin(currentPlayer.getSymbol());
                player1Turn = !player1Turn;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        System.out.println("Final Board:");
        gameField.printBoard();

        if (gameWon) {
            Player winner = player1Turn ? player2 : player1;
            System.out.println("Congratulations, " + winner.getName() + " wins!");
        } else {
            System.out.println("It's a draw!");
        }
    }
}

