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
    private static BoardChecker boardChecker;

    private static void fill(Stack<Board> boards, int id) throws Exception{
        if (id < 81) {
            if (id < 0) id = 80;
            System.out.println("fill start " + id);
            tempBoard = new Board(tempBoard);
            for (int zakres = 9; zakres > 0; zakres--) {
                tempBoard.fill(id % 9, id / 9, rand.nextInt(zakres) + 9 - zakres + 1);
                if(true) {
                //if (boardChecker.checkBoard(tempBoard)) {
                    //wrzuca dobrą próbę na stos
                    boards.push(tempBoard);
                    //odpala następny fill
                    if (id != 80) {
                        fill(boards, ++id);
                    }
                    else {
                        return;
                    }
                } else {
                    //ustawia poprzednią próbę w tempBoard
                    tempBoard = boards.pop();
                    //wrzuca tą próbę, bo pop ją zrzuciło przy okazji
                    boards.push(tempBoard);
                    //robi kopię do następnej próby w pętli
                    tempBoard = new Board(tempBoard);
                }
            }
            fill(boards, --id);
        }
    }

    public static Board fillBoard() throws Exception{
        boardChecker = new BoardChecker();
        tempBoard = new Board();
        boards = new Stack();
        boards.push(tempBoard);
        fill(boards, 0);
        return boards.pop();
    }
}
