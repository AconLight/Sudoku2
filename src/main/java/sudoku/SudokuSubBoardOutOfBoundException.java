package sudoku;

/**
 * Created by Wojciech on 2018-06-11.
 */
public class SudokuSubBoardOutOfBoundException extends SudokuBoardOutOfBoundException {
    public SudokuSubBoardOutOfBoundException() {
        super("SudokuSubBoardOutOfBoundException occured");
    }

    public SudokuSubBoardOutOfBoundException(String msg) {
        super(msg);
    }
}
