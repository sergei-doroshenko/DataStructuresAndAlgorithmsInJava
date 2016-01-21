package chap15_trie;

/**
 * Created by sergei on 11/5/15.
 */
public interface Vocabulary {
    boolean add(String word);
    boolean isPrefix(String prefix);
    boolean contains(String word);

    String getName();
}
