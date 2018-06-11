package sudoku;

/**
 * Created by Wojciech on 2018-06-11.
 */
public class SudokuDaoFactoryConnectionException extends SudokuRuntimeException {
    public SudokuDaoFactoryConnectionException() {
        super("SudokuDaoFactoryConnectionException occured");
    }

    public SudokuDaoFactoryConnectionException(String msg) {
        super(msg);
    }
}
