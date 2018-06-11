package sudoku;

/**
 * Created by Wojciech on 2018-06-11.
 */
public class SudokuFieldOutOfBoundException extends SudokuBoardOutOfBoundException {
    public SudokuFieldOutOfBoundException() {
        super("SudokuFieldOutOfBoundException occured");
    }

    public SudokuFieldOutOfBoundException(String msg) {
        super(msg);
    }
}
