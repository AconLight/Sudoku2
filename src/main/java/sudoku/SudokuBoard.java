package sudoku;

public class SudokuBoard {

    private SudokuField[][] board;
    private SudokuSubBoard[] columns, rows, boxes;

    private BoardChecker boardChecker;

    public SudokuBoard() {
        boardChecker = new BoardChecker();
        board = new SudokuField[9][9];
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                board[x][y] = new SudokuField(0);
            }
        }
        setElements();
    }

    private void setElements() {
        columns = new SudokuSubBoard[9];
        rows = new SudokuSubBoard[9];
        boxes = new SudokuSubBoard[9];

        SudokuField[] temp = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                temp[j] = board[i][j];
            }
            columns[i] = new SudokuSubBoard(temp);
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                temp[j] = board[j][i];
                System.out.print(temp[j].getValue());
            }
            rows[i] = new SudokuSubBoard(temp);
        }
        for (int a = 0; a < 9; a++) {
            for (int i = a % 3; i < a % 3 + 3; i++) {
                for (int j = a / 3; j < a / 3 + 3; j++) {
                    temp[j + i] = board[i][j];
                }
                boxes[a] = new SudokuSubBoard(temp);
            }
        }
    }

    public SudokuBoard(final int[][] boardContent) {
        boardChecker = new BoardChecker();
        board = new SudokuField[9][9];
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                board[x][y] = new SudokuField(boardContent[x][y]);
            }
        }
        setElements();
    }

    public SudokuBoard(final SudokuField[][] boardContent) {
        boardChecker = new BoardChecker();
        board = new SudokuField[9][9];
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                board[x][y] = new SudokuField(boardContent[x][y].getValue());
            }
        }
        setElements();
    }

    public SudokuBoard(final SudokuBoard b) {
        boardChecker = new BoardChecker();
        board = new SudokuField[9][9];
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                this.board[x][y] = new SudokuField(b.board[x][y].getValue());
            }
        }
        setElements();
    }

    public boolean checkBoard() {
        System.out.println("rows");

        for (SudokuSubBoard b : rows) {
            if (!b.verify()) {
                return false;
            }
        }
        System.out.println("boxes");

        int i = 0;
        for (SudokuSubBoard b : boxes) {
            i++;
            if (!b.verify(i)) {
                return false;
            }
        }
        System.out.println("columns");

        for (SudokuSubBoard b : columns) {
            if (!b.verify()) {
                return false;
            }
        }
        return true;
    }


    public int get(int x, int y) throws Exception {
        if (x < 0 || x >= 9 || y < 0 || y >= 9) {
            throw new Exception();
        }
        return board[x][y].getValue();
    }

    public void setBoard(final SudokuBoard b) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                this.board[x][y] = b.board[x][y];
            }
        }
    }

    public int getBoardValue(int x, int y) throws Exception {
        return get(x, y);
    }

    //TODO diffrent exceptions
    public void set(int x, int y, int value) throws Exception {
        if (x < 0 || x >= 9 || y < 0 || y >= 9) {
            throw new Exception();
        }
        if (value <= 0 || value > 9) {
            throw new Exception();
        }
        board[x][y].setValue(value);
    }

    public String toString() {
        String s = new String();
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                s += board[x][y].getValue();
                s += ", ";
            }
            s += "\n";
        }
        return s;
    }


    public SudokuField[][] getBoard() {
        return board;
    }

}
