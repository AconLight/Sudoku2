package sudoku;

/**
 * Created by Wojciech on 2018-06-11.
 */
public class SudokuBoardOutOfBoundGetException extends SudokuRuntimeException {
    public SudokuBoardOutOfBoundGetException() {
        super("SudokuBoardOutOfBoundGetException occured");
    }

    public SudokuBoardOutOfBoundGetException(String msg) {
        super(msg);
    }
}
