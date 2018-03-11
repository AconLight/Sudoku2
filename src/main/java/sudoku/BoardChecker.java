package sudoku;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BoardChecker {
    public boolean checkBoard(Board board) {
        return checkSubMatrices(board) && checkRowsAndColumns(board);
    }

    private boolean checkRowsAndColumns(Board board) {
        return checkRows(board, true) && checkRows(board, false);
    }

    private boolean checkRows(Board board, boolean isVertical) {
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

    private boolean checkSubMatrices(Board board) {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (!checkSubMatrix(board, x, y)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkSubMatrix(Board board, int subMatrixIndexX, int subMatrixIndexY) {
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

    private boolean checkRepetitions(List<Integer> analyzedNumbers) {
        Set<Integer> setOfAnalyzedNumbers = new HashSet(analyzedNumbers);
        return setOfAnalyzedNumbers.size() == analyzedNumbers.size();
    }
}
