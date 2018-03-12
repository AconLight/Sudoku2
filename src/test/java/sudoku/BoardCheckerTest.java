package sudoku;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardCheckerTest {
    @Test
    @DisplayName("BoardChecker tests")
    void correctBoardTest(TestInfo testInfo) {
        BoardChecker boardChecker = new BoardChecker();
        assertEquals(true, boardChecker.checkBoard(new Board(generateCorrectSudokuBoard())),
                "Checker is given a correct board");
    }

    @Test
    @DisplayName("BoardChecker tests")
    void emptyBoardTest(TestInfo testInfo) {
        BoardChecker boardChecker = new BoardChecker();
        assertEquals(true, boardChecker.checkBoard(new Board(generateEmptyBoard())),
                "Checker is given a board with mistake in sub matrix");
    }

    @Test
    @DisplayName("BoardChecker tests")
    void incorrectSubMatrixTest(TestInfo testInfo) {
        BoardChecker boardChecker = new BoardChecker();
        assertEquals(false, boardChecker.checkBoard(new Board(generateBoardWithBadSubMatrix())),
                "Checker is given a board with mistake in sub matrix");
    }

    @Test
    @DisplayName("BoardChecker tests")
    void incorrectColumnTest(TestInfo testInfo) {
        BoardChecker boardChecker = new BoardChecker();
        assertEquals(false, boardChecker.checkBoard(new Board(generateBoardWithBadColumn())),
                "Checker is given a board with mistake in sub matrix");
    }

    @Test
    @DisplayName("BoardChecker tests")
    void incorrectRowTest(TestInfo testInfo) {
        BoardChecker boardChecker = new BoardChecker();

        assertEquals(false, boardChecker.checkBoard(new Board(generateBoardWithBadRow())),
                "Checker is given a board with mistake in sub matrix");
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