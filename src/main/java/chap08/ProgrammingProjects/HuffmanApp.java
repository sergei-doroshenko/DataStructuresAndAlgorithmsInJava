package chap08.ProgrammingProjects;

/**
 * Created by user on 02.02.2015.
 */
public class HuffmanApp {
    public static void main (String[] args) {
        Huffman huffman = new Huffman();
        HuffmanTree t = huffman.createHuffmanTree("SUSIE SAYS IT IS EASY");
        t.displayTree();
        huffman.createHuffmanTable(t);
        String code = huffman.code("SUSIE SAYS IT IS EASY");
        System.out.println(code);
        String message = huffman.decode(code);
        System.out.println(message);
    }
}
