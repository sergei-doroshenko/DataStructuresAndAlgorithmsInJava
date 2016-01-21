package chap15_trie;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by sergei on 11/5/15.
 */
public class TreeVocabulary extends TreeSet<String> implements Vocabulary {
    private static final long serialVersionUID = 1084215309279053589L;


    public TreeVocabulary() {
        super();
    }

    public TreeVocabulary(Collection<String> c) {
        super(c);
    }

    public boolean isPrefix(String prefix) {
        String nextWord = ceiling(prefix); // return grater or equal
        if (nextWord == null) {
            return false;
        }
        if (nextWord.equals(prefix)) { // than we need next word (that grater)
            Set<String> tail = tailSet(nextWord, false); // Returns a view of the portion of this set
            // whose elements are greater than (or equal to, if inclusive is true) fromElement.
            if (tail.isEmpty()) {
                return false;
            }
            nextWord = tail.iterator().next();
        }
        return nextWord.startsWith(prefix);
    }

    /**
     * There is a mismatch between the parameter types of vocabulary and TreeSet, so
     * force call to the upper-class method
     */
    public boolean contains(String word) {
        return super.contains(word);
    }

    public String getName() {
        return getClass().getName();
    }
}
