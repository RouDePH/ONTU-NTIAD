package LW_3;

public class Player {
    private String name;
    private char symbol;

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName(){
        return name;
    }

    public char getSymbol(){
        return symbol;
    }

    public void makeMove(GameField field, int row, int col) {
        field.makeMove(row, col, symbol);
    }

    public void displayInfo() {
        System.out.println("Player: " + name);
        System.out.println("Symbol: " + symbol);
    }
}