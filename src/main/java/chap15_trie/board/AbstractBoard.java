package chap15_trie.board;

import chap15_trie.Vocabulary;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by sergei on 11/5/15.
 */
public abstract class AbstractBoard <T extends Vocabulary> {

    protected int rows;
    protected int cols;

    public final static class JuzzleBoardCell {
        int row;
        int col;

        public JuzzleBoardCell(int i, int j) {
            this.row = i;
            this.col = j;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof JuzzleBoardCell) {
                JuzzleBoardCell j = (JuzzleBoardCell) obj;
                return j.row == row && j.col == col;
            }
            return false;
        }

        @Override
        public int hashCode() {
            // works for up to 10 rows board
            return row * 10 + col;
        }
    }

    protected AbstractBoard(int r, int c, char[] letters) {
        rows = r;
        cols = c;
        if (letters.length != r * c) {
            throw new IllegalArgumentException(
                    "Wrong number of letters for board");
        }
    }

    public final int findWords(T vocabulary, ArrayList<List<BoardCell>> words) {
        LinkedList<BoardCell> usedCells = new LinkedList<>();
        int moves = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                BoardCell cell = new BoardCell(i, j);
                usedCells.add(cell);
                moves += recurse(vocabulary, usedCells, words);
                usedCells.pollLast();
            }
        }
        return moves;
    }

    protected abstract int recurse(T vocabulary,
                                   LinkedList<BoardCell> usedCells,
                                   ArrayList<List<BoardCell>> words);


    protected List<BoardCell> getPossibleMoves(LinkedList<BoardCell> usedCells) {

        // Possible moves are up/down/left/right/ur/ul/dr/dl from last cell
        LinkedList<BoardCell> moves = new LinkedList<>();
        BoardCell last = usedCells.peekLast();
        boolean u, d, r, l;
        u = last.row - 1 >= 0;
        d = last.row + 1 < rows;
        r = last.col + 1 < cols;
        l = last.col - 1 >= 0;
        if (u) { // up
            moves.add(new BoardCell(last.row - 1, last.col));
        }
        if (d) { // down
            moves.add(new BoardCell(last.row + 1, last.col));
        }

        if (r) { // right
            moves.add(new BoardCell(last.row, last.col + 1));
        }
        if (l) { // left
            moves.add(new BoardCell(last.row, last.col - 1));
        }
        if (u && l) { // up and left
            moves.add(new BoardCell(last.row - 1, last.col - 1));
        }
        if (u && r) { // up and right
            moves.add(new BoardCell(last.row - 1, last.col + 1));
        }
        if (d && l) { // down and left
            moves.add(new BoardCell(last.row + 1, last.col - 1));
        }
        if (d && r) { // down and right
            moves.add(new BoardCell(last.row + 1, last.col + 1));
        }
        // Don't go back
        moves.removeAll(usedCells);
        return moves;
    }

    protected abstract char getCharAt(int row, int col);

    protected String getWord(List<BoardCell> usedCells) {
        StringBuffer sb = new StringBuffer(usedCells.size());
        for (BoardCell cell : usedCells) {
            sb.append(getCharAt(cell.row, cell.col));
        }
        return sb.toString();
    }

    public class BoardCell {
        public int row;
        public int col;

        public BoardCell(int i, int j) {

        }
    }
}
