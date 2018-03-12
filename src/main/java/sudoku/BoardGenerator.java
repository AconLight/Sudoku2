package sudoku;

import java.util.Random;
import java.util.Stack;

/**
 * Created by Wojciech on 2018-03-11.
 */
public class BoardGenerator {

    private static Board tempBoard, tempBoard2;
    private static Stack<Board> boards;
    private static Random rand = new Random();
    private static BoardChecker boardChecker;
    private static boolean asd = true;

    private static boolean fill(Stack<Board> boards, int id) throws Exception{
            System.out.println("fill " + id);
            if (id != 81) {
                tempBoard = boards.pop();
                boards.push(tempBoard);
                tempBoard = new Board(tempBoard);

                int k = rand.nextInt(9);
                k = 0;
                for (int zakres = 1; zakres <= 9; zakres++) {
                    System.out.println("try " + ((zakres + k)%9+1));
                    //brudzi temBoard
                    tempBoard.fill(id % 9, id / 9, (zakres + k)%9+1);
                        if (boardChecker.checkBoard(tempBoard)) {
                        //wrzuca dobrą próbę na stos
                        boards.push(tempBoard);
                        //odpala następny fill
                        if (fill(boards, id+1)) {
                            return true;
                        } else {
                            //zrzucenie złej próby
                            boards.pop();
                            //czyści tempBoard
                            tempBoard = boards.pop();
                            boards.push(tempBoard);
                            tempBoard = new Board(tempBoard);
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
                System.out.println("wrong" + id);
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
