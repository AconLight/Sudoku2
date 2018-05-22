package sudoku;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

public class SudokuField implements Comparable<SudokuField>, Serializable {
    public int getValue() {
        return value;
    }

    public SudokuField(int value) {
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    private int value;


    @Override
    public int hashCode() {
        return new HashCodeBuilder(13, 37).
                append(value).
                toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        SudokuField rhs = (SudokuField) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(value, rhs.value)
                .isEquals();
    }

    public String toString() {
        return "asd";
    }

    public SudokuField clone() {
        return new SudokuField(value);
    }

    public int compareTo(SudokuField field) {
        return getValue() - field.getValue();
    }
}
