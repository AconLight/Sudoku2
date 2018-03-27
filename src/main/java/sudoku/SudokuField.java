package sudoku;

public class SudokuField {
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
}
