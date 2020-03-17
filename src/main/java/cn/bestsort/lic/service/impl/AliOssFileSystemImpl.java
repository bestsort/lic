package cn.bestsort.lic.service.impl;

import cn.bestsort.lic.model.entity.Files;
import cn.bestsort.lic.model.enums.FileStoreType;
import cn.bestsort.lic.service.ActualFileSystemInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author bestsort
 * @version 1.0
 * @date 3/7/20 11:20 AM
 */

@Lazy
@Slf4j
@Service
public class AliOssFileSystemImpl implements ActualFileSystemInterface {

    @Override
    public String makeDir(String path, String dirname, long userId) {
        return null;
    }

    @Override
    public boolean deleteFile(long fileId, long userId, boolean isDir) {
        return false;
    }

    @Override
    public String copyFileTo(String sourcePath, String targetPath, long fileId, long userId, boolean isMove) {
        return null;
    }

    @Override
    public Files renameFile(String sourceName, String targetName, long userId, long fileId) {
        return null;
    }

    @Override
    public FileStoreType getType() {
        return null;
    }
}
