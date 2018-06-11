package sudoku;

import java.util.Random;

/**
 * Created by Wojciech on 2018-06-11.
 */
public class BoardCleaner {
    public static void clean(SudokuBoard board, Level level) throws Exception {
        Random rand = new Random();
        int n = 10;
        switch (level) {
            case medium: n += 10;
            case hard: n += 10;
        }
        int x, y;
        for(int i = 0; i < n; i++) {
            x = rand.nextInt(9);
            y = rand.nextInt(9);
            while(board.get(x, y) == 0) {
                x = rand.nextInt(9);
                y = rand.nextInt(9);
            }
            board.set(rand.nextInt(9), rand.nextInt(9), 0);
        }
    }
}
