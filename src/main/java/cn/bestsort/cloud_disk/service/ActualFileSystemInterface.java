package cn.bestsort.cloud_disk.service;

import cn.bestsort.cloud_disk.model.enums.StorageType;

/**
 * TODO
 *
 * @author bestsort
 * @version 1.0
 * @date 2/29/20 2:47 PM
 */
public interface ActualFileSystemInterface extends BaseFileSystemInterface {
    StorageType getType();
}
