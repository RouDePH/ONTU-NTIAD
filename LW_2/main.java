package LW_2;

class Main {
    public static void main(String[] args) {
        final int treeLevels = 10; // Кількість рівнів ялинки
        final int arrayRows = 4; // Кількість рядків масиву
        final int arrayCols = 5; // Кількість стовпців масиву

        // Виведення ялинки
        TreeAndArray.printTree(treeLevels);
        
        // Виведення двовимірного масиву
        TreeAndArray.printIntArray(arrayRows, arrayCols);
    }

}
