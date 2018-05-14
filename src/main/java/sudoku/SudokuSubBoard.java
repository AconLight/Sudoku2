package sudoku;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.*;

public class SudokuSubBoard {
    public List<SudokuField> fields;

    public SudokuSubBoard(SudokuField[] fields) {
        //this.fields = new SudokuField[fields.length];
        //this.fields = new List

        SudokuField[] fields2 = new SudokuField[9];
        this.fields = Arrays.asList(fields2);

        for(int i = 0; i < fields.length; i++) {
            //this.fields.add(fields[i]);
            this.fields.set(i, fields[i]);

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SudokuSubBoard that = (SudokuSubBoard) o;
        return Objects.equals(fields, that.fields);
    }

    @Override
    public int hashCode() {

        return Objects.hash(fields);
    }

    @Override
    public String toString() {
        return "SudokuSubBoard{" +
                "fields=" + fields +
                '}';
    }

}
