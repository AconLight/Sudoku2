package sudoku;

/**
 */
public class App {
    public static void main(final String[] args) throws Exception {
        SudokuBoard board = new SudokuBoard();
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(board);


        SudokuBoard s2;

        SudokuSerializer.serialize(board);
        s2 = SudokuSerializer.deserialize();
        System.out.println(s2.toString());
        board = new SudokuBoard(board);
        board.set(0, 0, 3);
        System.out.println(board);
        if (board.checkBoard()) {
            System.out.println("ok");
        }
    }
}
