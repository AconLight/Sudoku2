package sudoku;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardCheckerTest2 {
    @Test
    public void correctBoardTest() {
        SudokuBoard b = new SudokuBoard(generateCorrectSudokuBoard());
        System.out.println(b.toString());
        assertEquals(true, b.checkBoard());
    }

    @Test
    public void emptyBoardTest() {
        assertEquals(true, new SudokuBoard(generateEmptyBoard()).checkBoard());
    }

    @Test
    public void incorrectSubMatrixTest() {
        assertEquals(false, new SudokuBoard(generateBoardWithBadSubMatrix()).checkBoard());
    }

    @Test
    public void incorrectColumnTest() {
        assertEquals(false, new SudokuBoard(generateBoardWithBadColumn()).checkBoard());
    }

    @Test
    public void incorrectRowTest() {
        assertEquals(false, new SudokuBoard(generateBoardWithBadRow()).checkBoard());
    }

    private int[][] generateCorrectSudokuBoard() {
        int[][] tab = {
                {2, 9, 5, 7, 4, 3, 8, 6, 1},
                {4, 3, 1, 8, 6, 5, 9, 2, 7},
                {8, 7, 6, 1, 9, 2, 5, 4, 3},
                {3, 8, 7, 4, 5, 9, 2, 1, 6},
                {6, 1, 2, 3, 8, 7, 4, 9, 5},
                {5, 4, 9, 2, 1, 6, 7, 3, 8},
                {7, 6, 3, 5, 2, 4, 1, 8, 9},
                {9, 2, 8, 6, 7, 1, 3, 5, 4},
                {1, 5, 4, 9, 3, 8, 6, 7, 2},};
        return tab;
    }

    private int[][] generateEmptyBoard() {
        int[][] tab = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},};
        return tab;
    }

    private int[][] generateBoardWithBadColumn() {
        int[][] tab = {
                {1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0},};
        return tab;
    }

    private int[][] generateBoardWithBadRow() {
        int[][] tab = {
                {1, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},};
        return tab;
    }

    private int[][] generateBoardWithBadSubMatrix() {
        int[][] tab = {
                {1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},};
        return tab;
    }

}