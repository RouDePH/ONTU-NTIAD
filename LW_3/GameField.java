package LW_3;

public class GameField {
    private char[][] board;
    private static final int SIZE = 3;

    public GameField() {
        board = new char[SIZE][SIZE];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j]);
                if (j < SIZE - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < SIZE - 1) {
                System.out.println("---------");
            }
        }
    }

    public boolean makeMove(int row, int col, char playerSymbol) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE || board[row][col] != ' ') {
            return false;
        }
        board[row][col] = playerSymbol;
        return true;
    }

    public boolean checkWin(char playerSymbol) {
        // Перевірка рядків
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] == playerSymbol && board[i][1] == playerSymbol && board[i][2] == playerSymbol) {
                return true;
            }
        }

        // Перевірка стовпців
        for (int i = 0; i < SIZE; i++) {
            if (board[0][i] == playerSymbol && board[1][i] == playerSymbol && board[2][i] == playerSymbol) {
                return true;
            }
        }

        // Перевірка діагоналей
        if (board[0][0] == playerSymbol && board[1][1] == playerSymbol && board[2][2] == playerSymbol) {
            return true;
        }
        if (board[0][2] == playerSymbol && board[1][1] == playerSymbol && board[2][0] == playerSymbol) {
            return true;
        }

        return false;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == ' ') {
                    return false; // Є вільні клітини на полі
                }
            }
        }
        return true; // Поле заповнено
    }
}