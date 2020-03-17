package cn.bestsort.lic.model.support;

import java.io.File;

/**
 *
 * @author bestsort
 * @version 1.0
 * @date 3/8/20 11:08 AM
 */
public class LicConst {

    /**
     * User home directory.
     */
    public static final String USER_HOME = System.getProperties().getProperty("user.home");

    /**
     * Version constant. (Available in production environment)
     */
    public static final String VERSION;

    /**
     * Path separator.
     */
    public static final String FILE_SEPARATOR = File.separator;


    static {
        // Set version
        VERSION = LicConst.class.getPackage().getImplementationVersion();
    }
}
