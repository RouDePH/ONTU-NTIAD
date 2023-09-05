package LW_2;

public class TreeAndArray {

    // Метод для виведення ялинки
    public static void printTree(int levels) {

        System.out.println("Ялинка:");

        for (int i = 0; i < levels; i++) {
            // Друк пробілів для вирівнювання
            for (int j = 0; j < levels - i - 1; j++) {
                System.out.print(" ");
            }

            // Друк зірочок
            for (int j = 0; j < i + 1; j++) {
                System.out.print("*");
            }

            // Перехід на новий рядок
            System.out.println();
        }
    }

    // Метод для створення і виведення двовимірного масиву цілих чисел
    public static void printIntArray(int rows, int cols) {
        int[][] array = new int[rows][cols];
        int value = 1;

        // Заповнення масиву та виведення його елементів

        System.out.println("\nМасив:");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i][j] = value;
                System.out.print(array[i][j] + "\t");
                value += 3;
            }
            System.out.println(); // Перехід на новий рядок
        }
    }

}