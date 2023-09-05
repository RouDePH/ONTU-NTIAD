package LW_4;

import LW_3.GameField;
import LW_3.Player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author postgresqltutorial.com
 */
import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String DB_NAME = "postgres";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/" + DB_NAME;
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";

    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            createPlayersTable(connection);

            Scanner scanner = new Scanner(System.in);
            String username1, username2;
            do {
                System.out.print("Enter Player 1 username: ");
                username1 = scanner.nextLine();
            } while (!isPlayerRegistered(connection, username1));

            do {
                System.out.print("Enter Player 2 username: ");
                username2 = scanner.nextLine();
            } while (!isPlayerRegistered(connection, username2));

            GameField gameField = new GameField();
            Player player1 = new Player(username1, 'X');
            Player player2 = new Player(username2, 'O');

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
                } while (row < 0 || row > 2 || col < 0 || col > 2
                        || !gameField.makeMove(row, col, currentPlayer.getSymbol()));

                moves++;
                gameWon = gameField.checkWin(currentPlayer.getSymbol());
                if (gameWon) {
                    Player winner = !player1Turn ? player2 : player1;
                    System.out.println("Congratulations, " + winner.getName() + " wins!");
                    updateWins(connection, winner.getName());
                } else {
                    player1Turn = !player1Turn;
                }
            }

            if (!gameWon) {
                System.out.println("It's a draw!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createPlayersTable(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS players (" +
                "id SERIAL PRIMARY KEY," +
                "username VARCHAR(255) NOT NULL UNIQUE," +
                "password VARCHAR(255) NOT NULL," +
                "wins INT DEFAULT 0)";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTableSQL);
        }
    }

    private static boolean isPlayerRegistered(Connection connection, String username) throws SQLException {
        String query = "SELECT COUNT(*) FROM players WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                int count = resultSet.getInt(1);
                return count > 0;
            }
        }
    }

    private static void updateWins(Connection connection, String username) throws SQLException {
        String updateWinsSQL = "UPDATE players SET wins = wins + 1 WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateWinsSQL)) {
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
        }
    }
}
