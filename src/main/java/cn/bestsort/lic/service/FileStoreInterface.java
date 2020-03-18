package cn.bestsort.lic.service;

import cn.bestsort.lic.model.enums.FileStoreType;
import cn.bestsort.lic.model.enums.propertys.FilePropertyEnum;

/**
 * 文件存储系统接口
 * @author bestsort
 * @version 1.0
 * @date 2/29/20 2:47 PM
 */
public interface FileStoreInterface extends BaseFileSystemInterface {
    /**
     * 每个具体实现类都需要实现该方法表示自己所支持的文件系统
     * @return FileStoreType 支持的文件系统
     */
    FileStoreType getStoreType();

    /**
     * 每个具体实现类都需要实现该方法表明自己所需要的配置字段
     * @return enum 需要的配置类
     */
    abstract Class<? extends FilePropertyEnum> getType();
    /**
     * 获取文件真实路径
     * @param parentDir 目标文件夹绝对路径
     * @param fileName  文件名
     * @return          文件全路径(绝对路径)
     */
    String getRealPath(String parentDir, String fileName);

    /**
     * 获取文件真实路径(根目录下)
     * @param fileName 文件名
     * @return         文件全路径
     */
    default String getRealPath(String fileName){
        return getRealPath("/", fileName);
    }
}
