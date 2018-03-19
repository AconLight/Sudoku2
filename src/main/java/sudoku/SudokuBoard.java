package sudoku;

public class SudokuBoard {

    private int[][] board;

    private BoardChecker boardChecker;

    public SudokuBoard() {
        boardChecker = new BoardChecker();
        board = new int[9][9];
    }

    public SudokuBoard(final int[][] boardContent) {
        boardChecker = new BoardChecker();
        board = boardContent;
    }

    public SudokuBoard(final SudokuBoard b) {
        boardChecker = new BoardChecker();
        board = new int[9][9];
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                this.board[x][y] = b.board[x][y];
            }
        }
    }

    public boolean checkBoard() {
        return boardChecker.checkBoard(this);
    }

    public int get(int x, int y) throws Exception {
        if (x < 0 || x >= 9 || y < 0 || y >= 9) {
            throw new Exception();
        }
        return board[x][y];
    }

    public void setBoard(final SudokuBoard b) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                this.board[x][y] = b.board[x][y];
            }
        }
    }

    public int getBoardValue(int x, int y) {
        return board[x][y];
    }

    //TODO diffrent exceptions
    public void set(int x, int y, int value) throws Exception {
        if (x < 0 || x >= 9 || y < 0 || y >= 9) {
            throw new Exception();
        }
        if (value <= 0 || value > 9) {
            throw new Exception();
        }
        board[x][y] = value;
    }

    public String toString() {
        String s = new String();
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                s += board[x][y];
                s += ", ";
            }
            s += "\n";
        }
        return s;
    }


    public int[][] getBoard() {
        return board;
    }

}
