package chap15_trie;

import java.util.Collection;

/**
 * Created by sergei on 11/5/15.
 */
public class TrieVocabulary implements Vocabulary {

    private boolean isWord = false;
    // The number of possible children is the number of letters in the alphabet
    private TrieVocabulary[] children = new TrieVocabulary[Alphabet.LOWERCASE.size()]; // size = 26
    // This is the number of actual children
    private int numChildren = 0;

    public TrieVocabulary() {
    }

    public TrieVocabulary(Collection<String> words) {
        for (String w:words) {
            add(w);
        }
    }

    public boolean add(String s) {
        char first = s.charAt(0);
        int index = Alphabet.LOWERCASE.getIndex(first);
        if (index < 0) {
            System.out.println("uf");
        }
        TrieVocabulary child = children[index];
        if (child == null) {
            child = new TrieVocabulary();
            children[index] = child;
            numChildren++;
        }
        if (s.length() == 1) {
            if (child.isWord) {
                // The word is already in the trie
                return false;
            }
            child.isWord = true;
            return true;
        } else {
            // Recurse into sub-trie
            return child.add(s.substring(1));
        }
    }

    /**
     * Searches for a string in this trie
     * @param s
     * @return
     */
    public boolean contains(String s) {
        TrieVocabulary n = getNode(s);
        return n != null && n.isWord;
    }

    /**
     * Searches for a string prefix in this trie
     * @param s
     * @return
     */
    public boolean isPrefix(String s) {
        TrieVocabulary n = getNode(s);
        return n != null && n.numChildren > 0;
    }

    /**
     * Returns the node corresponding to the string
     * @param s
     * @return
     */
    public TrieVocabulary getNode(String s) {
        TrieVocabulary node = this;
        for (int i = 0; i < s.length(); i++) {
            int index = Alphabet.LOWERCASE.getIndex(s.charAt(i));
            TrieVocabulary child = node.children[index];
            if (child == null) {
                // There is no such word
                return null;
            }
            node = child;
        }
        return node;
    }

    /**
     * Searches for a string represented as indices in this trie,
     * @return
     */
    public boolean contains(byte[] indices, int offset, int len) {
        TrieVocabulary n = getNode(indices, offset, len);
        return n != null && n.isWord;
    }

    public boolean contains(byte[] indices, int offset) {
        TrieVocabulary n = getNode(indices, offset, indices.length-offset);
        return n != null && n.isWord;
    }

    /**
     * Searches for a string prefix represented as indices in this trie
     * @return
     */
    public boolean isPrefix(byte[] indices, int offset, int len) {
        TrieVocabulary n = getNode(indices, offset, len);
        return n != null && n.numChildren > 0;
    }

    public boolean isPrefix(byte[] indices, int offset) {
        TrieVocabulary n = getNode(indices, offset, indices.length-offset);
        return n != null && n.numChildren > 0;
    }

    /**
     * Returns the node corresponding to the string represented as indices
     * @return
     */
    public TrieVocabulary getNode(byte[] indices, int offset, int len) {
        TrieVocabulary node = this;
        for (int i = 0; i < len; i++) {
            int index = indices[offset+i];
            TrieVocabulary child = node.children[index];
            if (child == null) {
                // There is no such word
                return null;
            }
            node=child;
        }
        return node;
    }

    public String getName() {
        return getClass().getName();
    }

    public boolean isWord() {
        return isWord;
    }

    public boolean hasChildren() {
        return numChildren > 0;
    }
}
