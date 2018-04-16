package sudoku;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.*;

public class SudokuSubBoard {
    public List<SudokuField> fields;

    public SudokuSubBoard(SudokuField[] fields) {
        //this.fields = new SudokuField[fields.length];
        //this.fields = new List

        for(int i = 0; i < fields.length; i++) {
            this.fields.add(fields[i]);
        }
    }

    public boolean verify() {
        Set<Integer> usedNumbers = new HashSet<Integer>();
        int count = 0;
        for (int i = 0; i < fields.size(); i++) {
            if (fields.get(i).getValue() != 0) {
                usedNumbers.add(fields.get(i).getValue());
                count ++;
            }
        }

        return usedNumbers.size() == count;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(13, 37).
                append(fields).
                toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        //SudokuSubBoard rhs = (SudokuSubBoard) obj;
        return false;//fields.containsAll(rhs.fields) && listB.containsAll(fields)
    }

    public String toString() {
        return "asd";
    }
}
