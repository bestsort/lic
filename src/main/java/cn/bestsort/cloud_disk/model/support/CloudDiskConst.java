package cn.bestsort.cloud_disk.model.support;

import java.io.File;

/**
 *
 * @author bestsort
 * @version 1.0
 * @date 3/8/20 11:08 AM
 */
public class CloudDiskConst {

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

    /**
     * Custom freemarker tag method key.
     */
    public static final String METHOD_KEY = "method";

    static {
        // Set version
        VERSION = CloudDiskConst.class.getPackage().getImplementationVersion();
    }
}
