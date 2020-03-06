package cn.bestsort.cloud_disk.file.utils;

import org.mockito.internal.util.StringUtil;

/**
 * 对文件路径的一些容错性处理
 * @author bestsort
 * @version 1.0
 * @date 2/29/20 8:38 PM
 */

public class FilePathUtil {

    private static String DATA_DIR = "/data";

    public static String ROOT_PATH = System.getProperty("user.dir") + DATA_DIR;

    private final static String FILE_SPLIT_CHARSET = "/";

    public static String tryAddStartAndEndCharset(String filePath){
        return tryAddPathEndCharset(tryAddPathStartCharset(filePath));
    }

    public static String tryAddPathEndCharset(String filePath){
        if (!filePath.endsWith(FILE_SPLIT_CHARSET)){
            return filePath + FILE_SPLIT_CHARSET;
        }
        return filePath;
    }

    public static String tryAddPathStartCharset(String filePath){
        if (!filePath.startsWith(FILE_SPLIT_CHARSET)){
            return FILE_SPLIT_CHARSET + filePath;
        }
        return filePath;
    }
    /**
     * 获取绝对路径
     * @param filePath 文件路径
     * @return 绝对路径
     */
    public static String getAbsolutePath(String filePath){
        if (!filePath.startsWith(ROOT_PATH)){
            return tryAddStartAndEndCharset(ROOT_PATH) + filePath;
        }
        return filePath;
    }

    /**
     * 合并路径名
     * @param prefix 路径
     * @param suffix 文件名
     * @return 合并结果
     */
    public static String unionPath(String prefix, String suffix){
        return tryAddStartAndEndCharset(prefix) +
                (suffix.startsWith(FILE_SPLIT_CHARSET) ? suffix.substring(1): suffix);
    }

    public static String unionAbsolutePath(String prefix, String suffix){
        return unionPath(getAbsolutePath(prefix), suffix);
    }

    /**
     * 获取相对据经
     * @param filePath 文件路径
     * @return 相对路径
     */
    public static String getContentPath(String filePath){
        if (filePath.startsWith(ROOT_PATH)){
            return filePath.substring(ROOT_PATH.length());
        }
        return filePath;
    }

}
