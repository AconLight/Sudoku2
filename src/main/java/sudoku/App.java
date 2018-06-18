package sudoku;

/**
 */
public class App {
    public static void main(final String[] args) throws Exception {
        SudokuBoard board = new SudokuBoard();
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(board);


    }
}
