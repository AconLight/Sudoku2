package sudoku;

public class Board {

    private int[][] board;

    public Board() {
        board = new int[9][9];
    }

    public Board(int[][] boardContent) {
        board = boardContent;
    }

    public Board(Board b) {
        board = new int[9][9];
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                this.board[x][y] = b.board[x][y];
            }
        }
    }

    public void set(Board b) {
        board = b.getBoard();
    }

    public int[][] getBoard() {
        return board;
    }

    public int getBoardValue(int x, int y) {
        return board[x][y];
    }

    //TODO diffrent exceptions
    public void fill(int x, int y, int value) throws Exception {
        if (x < 0 || x >= 9 || y < 0 || y >= 9) throw new Exception();
        if (value <= 0 || value > 9) throw new Exception();
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

    public void fillBoard() {
        try {
            BoardGenerator.fillBoard(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
