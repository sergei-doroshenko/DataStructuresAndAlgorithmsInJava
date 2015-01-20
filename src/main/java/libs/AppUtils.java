package libs;

import java.io.File;

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
