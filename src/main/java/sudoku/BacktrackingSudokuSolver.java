package sudoku;

import java.util.Random;
import java.util.Stack;

/**
 * Created by Wojciech on 2018-03-19.
 */
public class BacktrackingSudokuSolver implements SudokuSolver{
    private SudokuBoard tempBoard, tempBoard2;
    private Stack<SudokuBoard> boards;
    private Random rand = new Random();
    private BoardChecker boardChecker;

    public void solve(final SudokuBoard board) {
        boardChecker = new BoardChecker();
        tempBoard = new SudokuBoard();
        boards = new Stack();
        boards.push(tempBoard);
        try {
            fill(boards, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        board.setBoard(boards.pop());
    }


    private boolean fill(final Stack<SudokuBoard> boards, final int id) throws Exception {
        if (id != 81) {
            tempBoard = boards.pop();
            boards.push(tempBoard);
            tempBoard = new SudokuBoard(tempBoard);

            int k = rand.nextInt(9);
            for (int zakres = 1; zakres <= 9; zakres++) {
                //brudzi temBoard
                tempBoard.set(id % 9, id / 9, (zakres + k) % 9 + 1);
                //if (boardChecker.checkBoard(tempBoard)) {
                if (tempBoard.checkBoard()) {
                    //wrzuca dobrą próbę na stos
                    boards.push(tempBoard);
                    //odpala następny fill
                    if (fill(boards, id + 1)) {
                        return true;
                    } else {
                        //zrzucenie złej próby
                        boards.pop();
                        //czyści tempBoard
                        tempBoard = boards.pop();
                        boards.push(tempBoard);
                        tempBoard = new SudokuBoard(tempBoard);
                    }

                } else {
                    //ustawia poprzednią próbę w tempBoard
                    tempBoard = boards.pop();
                    //wrzuca tą próbę, bo pop ją zrzuciło przy okazji
                    boards.push(tempBoard);
                    //robi kopię do następnej próby w pętli
                    tempBoard = new SudokuBoard(tempBoard);
                }
            }
            return false;
        }
        return true;
    }
}
