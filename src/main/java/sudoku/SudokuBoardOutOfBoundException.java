package sudoku;

/**
 * Created by Wojciech on 2018-06-11.
 */
public class SudokuBoardOutOfBoundException extends SudokuRuntimeException {
    public SudokuBoardOutOfBoundException() {
        super("SudokuBoardOutOfBoundException occured");
    }

    public SudokuBoardOutOfBoundException(String msg) {
        super(msg);
    }
}
