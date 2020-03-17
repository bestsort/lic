package cn.bestsort.dubai.utils;

import static cn.bestsort.dubai.model.support.DuBaiConst.FILE_SEPARATOR;
import static cn.bestsort.dubai.model.support.DuBaiConst.USER_HOME;

/**
 * 对文件路径的一些容错性处理
 * @author bestsort
 * @version 1.0
 * @date 2/29/20 8:38 PM
 */

public class FilePathUtil {

    private static String DATA_DIR = FILE_SEPARATOR+"data";

    private static String ROOT_PATH = USER_HOME + DATA_DIR;

    public static String tryAddStartAndEndCharset(String filePath){
        return tryAddPathEndCharset(tryAddPathStartCharset(filePath));
    }

    public static String tryAddPathEndCharset(String filePath){
        if (!filePath.endsWith(FILE_SEPARATOR)){
            return filePath + FILE_SEPARATOR;
        }
        return filePath;
    }

    public static String tryAddPathStartCharset(String filePath){
        if (!filePath.startsWith(FILE_SEPARATOR)){
            return FILE_SEPARATOR + filePath;
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
                (suffix.startsWith(FILE_SEPARATOR) ? suffix.substring(1): suffix);
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

    private FilePathUtil(){}
}
