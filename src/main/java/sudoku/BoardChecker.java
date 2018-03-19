package sudoku;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BoardChecker {
    boolean checkBoard(final SudokuBoard board) {
        return checkSubMatrices(board) && checkRowsAndColumns(board);
    }

    private boolean checkRowsAndColumns(final SudokuBoard board) {
        return checkRows(board, true) && checkRows(board, false);
    }

    private boolean checkRows(final SudokuBoard board, boolean isVertical) {
        List<Integer> analyzedNumbers = new ArrayList();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (isVertical) {
                    if (board.getBoard()[i][j] != 0) {
                        analyzedNumbers.add(board.getBoard()[i][j]);
                    }
                } else {
                    if (board.getBoard()[j][i] != 0) {
                        analyzedNumbers.add(board.getBoard()[j][i]);
                    }
                }
            }
            if (!checkRepetitions(analyzedNumbers)) {
                return false;
            }
            analyzedNumbers = new ArrayList();
        }
        return true;
    }

    private boolean checkSubMatrices(final SudokuBoard board) {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (!checkSubMatrix(board, x, y)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkSubMatrix(final SudokuBoard board, final int subMatrixIndexX, final int subMatrixIndexY) {
        int x = subMatrixIndexX * 3;
        int y = subMatrixIndexY * 3;

        List<Integer> analyzedNumbers = new ArrayList();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getBoard()[x + i][y + j] != 0) {
                    analyzedNumbers.add(board.getBoard()[x + i][y + j]);
                }
            }
        }
        return checkRepetitions(analyzedNumbers);
    }

    private boolean checkRepetitions(final List<Integer> analyzedNumbers) {
        Set<Integer> setOfAnalyzedNumbers = new HashSet(analyzedNumbers);
        return setOfAnalyzedNumbers.size() == analyzedNumbers.size();
    }
}
