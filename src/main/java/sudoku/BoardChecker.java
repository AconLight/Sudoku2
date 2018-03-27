package sudoku;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BoardChecker {
    boolean checkBoard(final SudokuBoard board) throws Exception {
        return checkSubMatrices(board) && checkRowsAndColumns(board);
    }

    private boolean checkRowsAndColumns(final SudokuBoard board) throws Exception {
        return checkRows(board, true) && checkRows(board, false);
    }

    private boolean checkRows(final SudokuBoard board, boolean isVertical) throws Exception {
        List<Integer> analyzedNumbers = new ArrayList();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (isVertical) {
                    if (board.getBoard()[i][j].getValue() != 0) {
                        analyzedNumbers.add(board.get(i, j));
                    }
                } else {
                    if (board.get(i, j) != 0) {
                        analyzedNumbers.add(board.get(i, j));
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

    private boolean checkSubMatrices(final SudokuBoard board) throws Exception {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (!checkSubMatrix(board, x, y)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkSubMatrix(final SudokuBoard board, final int subMatrixIndexX, final int subMatrixIndexY) throws Exception {
        int x = subMatrixIndexX * 3;
        int y = subMatrixIndexY * 3;

        List<Integer> analyzedNumbers = new ArrayList();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.get(x + i, y + j) != 0) {
                    analyzedNumbers.add(board.get(x + i, y + j));
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
