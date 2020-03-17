package cn.bestsort.lic.service;

import cn.bestsort.lic.model.enums.FileStoreType;

/**
 * 文件存储系统接口
 * @author bestsort
 * @version 1.0
 * @date 2/29/20 2:47 PM
 */
public interface FileStoreInterface extends BaseFileSystemInterface {
    /**
     * 获取实现所对应的文件系统
     * @return FileStoreType
     */
    FileStoreType getStoreType();


}
