package sudoku;

/**
 * Created by Wojciech on 2018-06-11.
 */
public class SudokuRuntimeException extends Exception {
    public SudokuRuntimeException() {
        super("SudokuRuntimeException occured");
    }

    public SudokuRuntimeException(String msg) {
        super(msg);
    }
}
