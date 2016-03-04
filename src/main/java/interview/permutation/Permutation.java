package interview.permutation;

/**
 * Created by Sergei_Admin on 02.03.2016.
 */
public class Permutation {

    public void permutation(char[] chars, int offcet){
        for (int i = 0; i < chars.length-offcet; i++) {
            if (offcet <= chars.length-2) {
                permutation(chars, offcet+1);
            } else {
                System.out.println(new String(chars));
            }

            rotate(chars, offcet);
        }
    }

    public void rotate(char[] chars, int offset) {
        char temp = chars[offset];
        for (int i = offset; i < chars.length-1; i++) {
            chars[i] = chars[i+1];
        }
        chars[chars.length-1] = temp;
    }

    public static void main(String[] args) {
        Permutation p = new Permutation();
        String text = "ABCD";
        p.permutation(text.toCharArray(), 0);
    }
}
