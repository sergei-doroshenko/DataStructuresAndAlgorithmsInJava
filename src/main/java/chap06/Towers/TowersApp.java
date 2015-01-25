package chap06.Towers;

/**
 * Created by Sergei Doroshenko on 25.01.2015.
 */
public class TowersApp {
    static int nDisks = 3;

    public static void main(String[] args) {
        doTowers(nDisks, 'A', 'B', 'C');
    }
    //-----------------------------------------------------------
    public static void doTowers(int topN, char src, char inter, char dest) {
        String offcet = "";
        for (int i = 3; i > topN; i--) offcet += "   ";
        System.out.println(offcet + "Enter ("+ topN +" disks): s = "+src+", i = "+inter+", d = "+dest);


        if(topN == 1) {
            System.out.println(offcet + "Disk 1 from " + src + " to " + dest);
        } else {
            doTowers(topN - 1, src, dest, inter);   // src to inter

            // move bottom
            System.out.println(offcet + "Disk " + topN + " from " + src + " to "+ dest);

            doTowers(topN - 1, inter, src, dest);   // inter to dest
        }
    }
}
