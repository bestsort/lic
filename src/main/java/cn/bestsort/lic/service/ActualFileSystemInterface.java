package cn.bestsort.dubai.service;

import cn.bestsort.dubai.model.enums.StorageType;

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
