package sudoku;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class SudokuBoard implements Serializable{

    private List<List<SudokuField>> board;
    private  List<SudokuSubBoard> columns, rows, boxes;

    private BoardChecker boardChecker;

    private final static Logger logger = Logger.getLogger(LoggerManager.class.getName());

    public SudokuBoard() {
        boardChecker = new BoardChecker();
        List<SudokuField> fields[] = new List[9];
        board = Arrays.asList(fields);

        for (int x = 0; x < 9; x++) {
            board.set(x, Arrays.asList(new SudokuField[9]));
            for (int y = 0; y < 9; y++) {
                board.get(x).set(y, new SudokuField(0));
            }
        }
        setElements();
    }

    private void setElements() {
        columns = Arrays.asList(new SudokuSubBoard[9]);
        rows = Arrays.asList(new SudokuSubBoard[9]);
        boxes = Arrays.asList(new SudokuSubBoard[9]);

        SudokuField[] temp = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                temp[j] =  board.get(i).get(j);
            }
            columns.set(i, new SudokuSubBoard(temp));
        }

        temp = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                temp[j] =  board.get(j).get(i);
            }
            rows.set(i, new SudokuSubBoard(temp));
        }

        temp = new SudokuField[9];
        for (int a = 0; a < 9; a++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    temp[j*3 + i] = board.get(a%3*3 + i).get(a/3*3 + j);
                }
            }
            boxes.set(a, new SudokuSubBoard(temp));
        }
    }

    public SudokuBoard(final int[][] boardContent) {
        boardChecker = new BoardChecker();
        List<SudokuField> fields[] = new List[9];
        board = Arrays.asList(fields);
        for (int x = 0; x < 9; x++) {
            board.set(x, Arrays.asList(new SudokuField[9]));
            for (int y = 0; y < 9; y++) {
                board.get(x).set(y, new SudokuField(boardContent[x][y]));
            }
        }
        setElements();
    }

    public SudokuBoard(final SudokuField[][] boardContent) {
        boardChecker = new BoardChecker();
        List<SudokuField> fields[] = new List[9];
        board = Arrays.asList(fields);
        for (int x = 0; x < 9; x++) {
            board.set(x, Arrays.asList(new SudokuField[9]));
            for (int y = 0; y < 9; y++) {
                board.get(x).set(y, new SudokuField(boardContent[x][y].getValue()));
            }
        }
        setElements();
    }

    public SudokuBoard(final SudokuBoard b) {
        boardChecker = new BoardChecker();
        List<SudokuField> fields[] = new List[9];
        board = Arrays.asList(fields);
        for (int x = 0; x < 9; x++) {
            board.set(x, Arrays.asList(new SudokuField[9]));
            for (int y = 0; y < 9; y++) {
                this.board.get(x).set(y, new SudokuField(b.board.get(x).get(y).getValue()));
            }
        }
        setElements();
    }

    public SudokuBoard clone() {
        return new SudokuBoard(this);
    }

    public boolean checkBoard() {

        for (SudokuSubBoard b : rows) {
            if (!b.verify()) {
                return false;
            }
        }

        int i = 0;
        for (SudokuSubBoard b : boxes) {
            i++;
            if (!b.verify()) {
                return false;
            }
        }

        for (SudokuSubBoard b : columns) {
            if (!b.verify()) {
                return false;
            }
        }
        return true;
    }


    public int get(int x, int y) throws SudokuBoardOutOfBoundGetException {
        if (x < 0 || x >= 9 || y < 0 || y >= 9) {
            throw new SudokuBoardOutOfBoundGetException();
        }
        return board.get(x).get(y).getValue();
    }

    public void setBoard(final SudokuBoard b) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                this.board.get(x).set(y, new SudokuField(b.board.get(x).get(y).getValue()));
            }
        }
    }

    public int getBoardValue(int x, int y) throws Exception {
        return get(x, y);
    }

    public void set(int x, int y, int value) throws SudokuRuntimeException {
        if (x < 0 || x >= 9 || y < 0 || y >= 9) {
            throw new SudokuBoardOutOfBoundGetException();
        }
        board.get(x).get(y).setValue(value);
    }

    public String toString() {
        String s = new String();
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                s += board.get(x).get(y).getValue();
                s += ", ";
            }
            s += "\n";
        }
        return s;
    }


    public List<List<SudokuField>> getBoard() {
        return board;
    }

}
