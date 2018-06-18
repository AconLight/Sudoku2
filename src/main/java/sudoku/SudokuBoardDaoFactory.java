package sudoku;

/**
 * Created by Wojciech on 2018-06-18.
 */
public class SudokuBoardDaoFactory {

    public static Dao getFileDao(String fileName) {
        return new FileSudokuBoardDao(fileName);
    }

    public static Dao getDatabaseDao(String fileName) {
        return new JbdcSudokuBoardDaoFactory(fileName);
    }
}
