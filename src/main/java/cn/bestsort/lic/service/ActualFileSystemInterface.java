package cn.bestsort.lic.service;

import cn.bestsort.lic.model.enums.FileStoreType;

/**
 * TODO
 *
 * @author bestsort
 * @version 1.0
 * @date 2/29/20 2:47 PM
 */
public interface ActualFileSystemInterface extends BaseFileSystemInterface {
    FileStoreType getType();
}
