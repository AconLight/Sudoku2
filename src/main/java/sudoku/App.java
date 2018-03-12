package sudoku;

/**
 */
public class App {
    public static void main(String[] args) throws Exception{
        Board board = new Board();
        board.fillBoard();
        System.out.println(board.toString());
    }
}
