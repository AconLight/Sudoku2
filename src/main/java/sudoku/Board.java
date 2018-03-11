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

    public void fill(int x, int y, int value) {

    }
}
