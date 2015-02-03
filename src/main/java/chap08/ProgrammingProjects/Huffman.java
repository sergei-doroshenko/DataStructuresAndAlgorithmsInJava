package chap08.ProgrammingProjects;

import chap04.PriorityQ.PriorityQO;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 02.02.2015.
 */
public class Huffman {
    private HuffmanTree tree;
    private Map<Character, String> codeMap;
    private Map<String, Character> decodeMap;

    public HuffmanTree createHuffmanTree(String input) {

        PriorityQO<HuffmanTree> q = new PriorityQO<>(20, new Comparator<HuffmanTree>() {
            @Override
            public int compare(HuffmanTree o1, HuffmanTree o2) {
                return o2.getRoot().frequency - o1.getRoot().frequency;
            }
        });

        char[] chars = new char[input.length()];
        int ind = 0;
        for(int j = 0; j < input.length(); j++) {   // for each char,
            char ch = input.charAt(j);              // read from input
            if (!contains(chars, ch)) {
                chars[ind++] = ch;
                int freq = 0;
                for (int i = 0; i < input.length(); i++) {
                    if (ch == input.charAt(i)) freq++;
                }
                HuffmanNode node = new HuffmanNode(ch, freq);
                HuffmanTree tree = new HuffmanTree();
                tree.setRoot(node);
                q.insert(tree);
            }
        }

        while (q.size() > 1) {
            HuffmanTree ht1 = q.remove();
            HuffmanTree ht2 = q.remove();

            HuffmanNode node = new HuffmanNode();
            node.frequency = ht1.getRoot().frequency + ht2.getRoot().frequency;
            node.leftChild = ht1.getRoot();
            node.rightChild = ht2.getRoot();

            HuffmanTree ht3 = new HuffmanTree();
            ht3.setRoot(node);
            q.insert(ht3);
        }
        HuffmanTree tree = q.remove();
        this.tree = tree;
        return tree;
    }

    public Map createHuffmanTable(HuffmanTree tree) {
        Map<Character, String> map = new HashMap<>();

        preOrder(tree.getRoot(), "", map);
        System.out.println(map);
        this.codeMap = map;
        this.decodeMap = new HashMap<>();
        for (Map.Entry<Character, String> e : map.entrySet()) {
            decodeMap.put(e.getValue(), e.getKey());
        }
        return map;
    }


    private void preOrder(HuffmanNode localRoot, String st, Map<Character,String> map) {

        if(localRoot != null) {
            //System.out.print(" [" + localRoot.character + ", " + localRoot.frequency + ", " + st + "]");
            if (localRoot.leftChild == null && localRoot.rightChild == null) {
                map.put(localRoot.character, st);
            }
            preOrder(localRoot.leftChild, st + "0", map);
            preOrder(localRoot.rightChild, st + "1", map);
        }
    }

    public boolean contains(char[] chars, char ch) {
        for (char c : chars) {
            if (c == ch) return true;
        }
        return false;
    }

    public String code(String message) {
        HuffmanTree tree = createHuffmanTree(message);
        Map<Character, String> map = createHuffmanTable(tree);
        String code = "";
        for(int j = 0; j < message.length(); j++) {   // for each char,
            char ch = message.charAt(j);              // read from input
            String c = map.get(ch);
            code += c + " ";
        }
        return code;
    }

    public String decode(String code) {
        String message = "";
        String[] tokkens = code.split(" ");
        for (int i = 0; i < tokkens.length; i++) {
            message += decodeMap.get(tokkens[i]);
        }
        return message;
    }
}
