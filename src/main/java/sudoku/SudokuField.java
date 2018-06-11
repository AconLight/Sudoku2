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

    public void setValue(int value) throws SudokuFieldOutOfBoundException {
        if (value < 0 || value > 9) throw new SudokuFieldOutOfBoundException();
        this.value = value;
    }

    private int value;
    private boolean canBeModify;

    public void setCanBeModify(boolean b) {canBeModify = b;}
    public boolean getCanBeModify() {return canBeModify;}

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
