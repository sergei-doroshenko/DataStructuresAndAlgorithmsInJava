package chap15_trie;

import chap15_trie.board.AbstractBoard;
import chap15_trie.board.AlphabetBoard;
import chap15_trie.board.Board;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergei on 11/5/15.
 */
public class VocabularyTests {
    public static void main(String[] args) throws Exception {

        // Load the words from the resource file
        InputStream in = VocabularyTests.class.getResourceAsStream("/words_en.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        ArrayList<String> words = new ArrayList<>(150000);
        ArrayList<byte[]> wordsInt = new ArrayList<>(150000);
        do {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            if (line.matches("[a-z]+")) {
                words.add(line);
                wordsInt.add(Alphabet.LOWERCASE.toInt(line));
            }
        } while (true);
        reader.close();

        // Create the three vocabularies
        ListVocabulary listVocabulary = new ListVocabulary(words);
        TreeVocabulary treeVocabulary = new TreeVocabulary(words);
        TrieVocabulary trieVocabulary = new TrieVocabulary(words);

        Vocabulary vocabularies[] = { listVocabulary, treeVocabulary,
                trieVocabulary };

        // Let's do some testing
        // First, check for random words from the dictionary

        // Create a test set
        int testSetSize = 20_000_000;
        int MAX_BOARDS = 15_000;
        List<String> testSet = new ArrayList<String>(testSetSize);
        List<byte[]> testSetInt = new ArrayList<>(testSetSize);
        for (int i = 0; i < testSetSize; i++) {
            int r = (int) (Math.random() * words.size());
            testSet.add(words.get(r));
            testSetInt.add(wordsInt.get(r));
        }

        // Check the testset against all three implementations

        for (Vocabulary v : vocabularies) {
            System.out.println("Checking vocabulary " + v.getName());
            long start = System.currentTimeMillis();
            int found = 0;
            for (String word : testSet) {
                if (v.contains(word)) {
                    found++;
                }
            }
            System.out.println("Completed in "
                    + (System.currentTimeMillis() - start) + " ms. Found "
                    + found);

        }
        {
            System.out.println("Checking vocabulary TrieVocabulary with int[]");
            long start = System.currentTimeMillis();
            int found = 0;
            for (byte[] word : testSetInt) {
                if (trieVocabulary.contains(word,0)) {
                    found++;
                }
            }
            System.out.println("Completed in "
                    + (System.currentTimeMillis() - start) + " ms. Found " + found);

        }

        // The test set is randomized. Using 16 letters at a time from the test set
        String boardLetters="";
        List<Board> testBoards = new ArrayList<>();
        List<AlphabetBoard> alphabetTestBoards = new ArrayList<>();

        TEST_SET: for(int i=0; i < testSetSize;i++) {
            boardLetters+=testSet.get(i);
            while(boardLetters.length()>= 16) {
                char[] letters = boardLetters.substring(0, 16).toCharArray();
                Board board = new Board(letters);
                AlphabetBoard alphabetBoard = new AlphabetBoard(letters);
                testBoards.add(board);
                alphabetTestBoards.add(alphabetBoard);

                if(testBoards.size() == MAX_BOARDS) {
                    break TEST_SET;
                }
                boardLetters = boardLetters.substring(16);
            }
        }

        System.out.println("Created "+testBoards.size()+" boards");
        for (Vocabulary v : vocabularies) {
            int numWords = 0;
            double avgMoves = 0;
            System.out.println("Checking boards with vocabulary " + v.getName());
            long start = System.currentTimeMillis();
            for(Board board : testBoards) {
                ArrayList<List<AbstractBoard<Vocabulary>.BoardCell>> foundWords = new ArrayList<>();
                avgMoves += board.findWords(v, foundWords);
                numWords += foundWords.size();
            }
            avgMoves = avgMoves/testBoards.size();
            System.out.println("Completed in "
                    + (System.currentTimeMillis() - start) + " ms. Found "
                    + numWords+ ". Average moves: "+avgMoves);

        }

        {
            int numWords = 0;
            double avgMoves = 0;
            System.out.println("Checking boards with vocabulary " + trieVocabulary.getName()+ " using indices");
            long start = System.currentTimeMillis();
            for(AlphabetBoard board : alphabetTestBoards) {
                ArrayList<List<AbstractBoard<TrieVocabulary>.BoardCell>> foundWords = new ArrayList<>();
                avgMoves += board.findWords(trieVocabulary, foundWords);
                numWords += foundWords.size();
            }
            avgMoves = avgMoves/alphabetTestBoards.size();
            System.out.println("Completed in "
                    + (System.currentTimeMillis() - start) + " ms. Found "
                    + numWords+ ". Average moves: "+avgMoves);


        }
    }
}
