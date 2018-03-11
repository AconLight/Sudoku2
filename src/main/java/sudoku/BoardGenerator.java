package sudoku;

import java.util.Random;
import java.util.Stack;

/**
 * Created by Wojciech on 2018-03-11.
 */
public class BoardGenerator {

    private static Board tempBoard;
    private static Stack<Board> boards;
    private static Random rand = new Random();
    private static int zakres;

    public static void fill(Stack<Board> boards, int id) throws Exception{



        tempBoard = new Board(tempBoard);
        for(int zakres = 9; zakres > 0; zakres--) {
            tempBoard.fill(id%9, id/9, rand.nextInt(zakres)+zakres-9+1);
            //if (BoardChecker.check(board)) {
            if (true) {
                //wrzuca dobrą próbę na stos
                boards.push(tempBoard);

                break;
            }
            else {
                //ustawia poprzednią próbę w tempBoard
                tempBoard = boards.pop();
                //wrzuca tą próbę, bo pop ją zrzuciło przy okazji
                boards.push(tempBoard);
                //robi kopię do następnej próby w pętli
                tempBoard = new Board(tempBoard);
            }

        }
    }

    public void fillBoard(Board board) throws Exception{
        tempBoard = board;
        boards = new Stack();
        boards.push(tempBoard);
       // fill(boards);

    }
}
