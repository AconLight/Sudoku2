package sudoku;

/**
 * Created by Wojciech on 2018-03-11.
 */
public class Board {

    public int[][] getBoard() {
        return board;
    }

    private int[][] board;

    public Board() {
        board = new int[9][9];
    }

    //TODO diffrent exceptions
    public void fill(int x, int y, int value) throws Exception {
        if (x < 0 || x >= 9 || y < 0 || y >= 9) throw new Exception();
        if (value <= 0 || value > 9) throw new Exception();
        board[x][y] = value;
    }
}
