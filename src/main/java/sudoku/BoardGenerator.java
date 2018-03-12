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
    private static boolean asd = true;

    private static boolean fill(Stack<Board> boards, int id) throws Exception{
            //System.out.println(id);
            if (id != 81) {
                tempBoard = new Board(tempBoard);
                int k = rand.nextInt(9);
                for (int zakres = 1; zakres <= 9; zakres++) {
                    tempBoard.fill(id % 9, id / 9, (zakres + k)%9+1);
                        if (boardChecker.checkBoard(tempBoard)) {
                       // if(false) {
                        //wrzuca dobrą próbę na stos
                        boards.push(tempBoard);
                        //odpala następny fill
                        if (fill(boards, ++id)) {
                            return true;
                        } else {

                            //boards.pop();
                            tempBoard = boards.pop();
                            boards.push(tempBoard);
                            //robi kopię do następnej próby w pętli
                            tempBoard = new Board(tempBoard);
                        }

                    } else {
                       // System.out.print("w");
                        //ustawia poprzednią próbę w tempBoard
                        tempBoard = boards.pop();
                        //wrzuca tą próbę, bo pop ją zrzuciło przy okazji
                        boards.push(tempBoard);
                        //robi kopię do następnej próby w pętli
                        tempBoard = new Board(tempBoard);
                    }
                }
                //System.out.println("wrong" + id);
                return false;
            }
            return true;
    }

    public static Board fillBoard() throws Exception{
        asd = true;
        boardChecker = new BoardChecker();
        tempBoard = new Board();
        boards = new Stack();
        boards.push(tempBoard);
        fill(boards, 0);
        return boards.pop();
    }
}
