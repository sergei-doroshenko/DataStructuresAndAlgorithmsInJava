package interview.permutation;

/**
 * Created by Sergei_Admin on 02.03.2016.
 */
public class Permutation {


    public void permutation(int len, String str){
        if (len == str.length()-2) {
            System.out.println(str);
        } else {
            for (int i = 0; i < len; i++) {
                permutation(len+1, rotate(str, len));

            }
        }
    }

    public String rotate(String str, int offset) {
        char[] chars = str.toCharArray();
        char temp = chars[offset];
        for (int i = offset; i < chars.length-1; i++) {
            chars[i] = chars[i+1];
        }
        chars[chars.length-1] = temp;
        return new String(chars);
    }

    public static void main(String[] args) {
        Permutation permutation = new Permutation();
        String text = "ABCD";
        for (int i = 0; i< text.length(); i++) {
            permutation.permutation(0, text);
            text = permutation.rotate(text,0);
        }
    }
}
