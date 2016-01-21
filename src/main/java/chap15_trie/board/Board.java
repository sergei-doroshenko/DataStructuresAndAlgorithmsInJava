package chap15_trie.board;

import chap15_trie.Vocabulary;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by sergei on 11/5/15.
 */
public class Board extends AbstractBoard<Vocabulary> {

    char[][] board;

    /**
     * Creates a default 4x4 board
     *
     * @param letters
     */
    public Board(char[] letters) {
        this(4, 4, letters);
    }

    public Board(int r, int c, char[] letters) {
        super(r, c, letters);
        board = new char[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int k = i * c + j;
                board[i][j] = letters[k];
            }
        }
    }

    @Override
    protected int recurse(Vocabulary vocabulary,
                          LinkedList<BoardCell> usedCells, ArrayList<List<BoardCell>> words) {
        int r = 1;
        String word = getWord(usedCells);
        if (vocabulary.contains(word)) {
            // System.out.println(word);
            words.add(new ArrayList<>(usedCells));
        }
        if (vocabulary.isPrefix(word)) {
            // Search for moves
            List<BoardCell> moves = getPossibleMoves(usedCells);
            for (BoardCell move : moves) {
                usedCells.add(move);
                r += recurse(vocabulary, usedCells, words);
                usedCells.pollLast();
            }
        }
        return r;
    }

    @Override
    protected char getCharAt(int row, int col) {
        return board[row][col];
    }
}
