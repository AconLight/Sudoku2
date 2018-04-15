package sudoku;

import java.util.HashSet;
import java.util.Set;

public class SudokuSubBoard {
    public SudokuField[] fields;

    public SudokuSubBoard(SudokuField[] fields) {
        //this.fields = fields;
        this.fields = new SudokuField[fields.length];
        for(int i = 0; i < fields.length; i++) {
            this.fields[i] = fields[i];
        }
    }

    public boolean verify() {
        Set<Integer> usedNumbers = new HashSet<Integer>();
        int count = 0;
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getValue() != 0) {
                usedNumbers.add(fields[i].getValue());
                count ++;
            }
        }

        return usedNumbers.size() == count;
    }
}
