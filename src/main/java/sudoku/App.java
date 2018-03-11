package sudoku;

/**
 */
public class App {
    public static void main(String[] args) throws Exception{
        System.out.println("Hello World!");
        Board board = new Board();
        System.out.println(board.toString());
       // for (int i = 0; i < 81
        System.out.println(BoardGenerator.fillBoard().toString());
    }
}
