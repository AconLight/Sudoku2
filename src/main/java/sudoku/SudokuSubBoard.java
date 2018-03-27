package sudoku;

import java.util.HashSet;
import java.util.Set;

public class SudokuSubBoard {
    private SudokuField[] fields;

    public SudokuSubBoard(SudokuField[] fields) {
        this.fields = fields;
    }

    public boolean verify() {
        int count = 0;
        Set<Integer> usedNumbers = new HashSet<Integer>();

        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getValue() != 0) {
                usedNumbers.add(fields[i].getValue());
            }
        }

        if (usedNumbers.size() != count) {
            System.out.println("XD");

            for (int i = 0; i < fields.length; i++) {
                System.out.println(fields[i].getValue());
            }
        }

        return usedNumbers.size() == count;
    }
    public boolean verify(int x) {
        System.out.println("ROW ROW ROW     " + x);
        int count = 0;
        Set<Integer> usedNumbers = new HashSet<Integer>();

        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getValue() != 0) {
                usedNumbers.add(fields[i].getValue());
            }
        }

        if (usedNumbers.size() != count) {
            System.out.println("XD");

            for (int i = 0; i < fields.length; i++) {
                System.out.println(fields[i].getValue());
            }
        }

        return usedNumbers.size() == count;
    }
}
