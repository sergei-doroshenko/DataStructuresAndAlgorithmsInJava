package libs;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by user on 20.01.2015.
 */
public final class AppUtils {

    private final ClassLoader classLoader = getClass().getClassLoader();
    private static AppUtils instance;

    private AppUtils() {}

    public static AppUtils getInstance() {
        if (instance == null) {
            instance = new AppUtils();
        }
        return instance;
    }

    //-------------------------------------------------------------
    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        br.close();
        isr.close();
        return s;
    }
    //-------------------------------------------------------------
    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }
    //-------------------------------------------------------------
    /**
     * //Get file from resources folder
     * @param fileName
     * @return
     */
    public File getFileFromResources (String fileName) {
        //ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(fileName).getFile());
    }
}
