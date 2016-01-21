package chap15_trie.board;

import chap15_trie.Alphabet;
import chap15_trie.TrieVocabulary;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by sergei on 11/5/15.
 */
public class AlphabetBoard extends AbstractBoard<TrieVocabulary> {
    byte[][] alphabetBoard;

    /**
     * Creates a default 4x4 board
     *
     * @param letters
     */
    public AlphabetBoard(char[] letters) {
        this(4, 4, letters);
    }

    public AlphabetBoard(int r, int c, char[] letters) {
        super(r, c, letters);
        alphabetBoard = new byte[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int k = i * c + j;
                alphabetBoard[i][j] = Alphabet.LOWERCASE.getIndex(letters[k]);
            }
        }
    }

    protected int recurse(TrieVocabulary vocabulary,
                          LinkedList<BoardCell> usedCells, ArrayList<List<BoardCell>> words) {
        int r = 1;
        byte[] word = getInt(usedCells);
        if (vocabulary.contains(word, 0)) {
            // System.out.println(word);
            words.add(new ArrayList<>(usedCells));
        }
        if (vocabulary.isPrefix(word, 0)) {
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

    protected byte[] getInt(List<BoardCell> list) {
        byte[] r = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            BoardCell cell = list.get(i);
            r[i] = alphabetBoard[cell.row][cell.col];
        }
        return r;
    }

    @Override
    protected char getCharAt(int row, int col) {
        return Alphabet.LOWERCASE.alphabet.charAt(alphabetBoard[row][col]);
    }
}
