package cn.bestsort.cloud_disk.service.impl;

import cn.bestsort.cloud_disk.file.dao.FilesDao;
import cn.bestsort.cloud_disk.file.entity.Files;
import cn.bestsort.cloud_disk.model.enums.StorageType;
import cn.bestsort.cloud_disk.service.CloudDiskFileSystemInterface;
import cn.bestsort.cloud_disk.handler.FileStorageHandlers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * TODO
 *
 * @author bestsort
 * @version 1.0
 * @date 3/2/20 10:17 AM
 */
@Slf4j
@Service
public class CloudDiskFileSystemImpl implements CloudDiskFileSystemInterface {

    @Resource
    private FilesDao filesDao;

    @Resource
    private FileStorageHandlers fileHandlers;
    private StorageType type = StorageType.DEFAULT;
    @Override
    public List<Files> listFiles(String path, long userId) {
        return null;
    }

    @Override
    public String makeDir(String path, String dirname, long userId) {
        return fileHandlers.getStrategy(type).makeDir(path, dirname, userId);
    }

    @Override
    public boolean deleteFile(long fileId, long userId, boolean isDir) {
        return fileHandlers.getStrategy(type).deleteFile(fileId, userId, isDir);
    }

    @Override
    public String copyFileTo(String sourcePath, String targetPath,long fileId, long userId, boolean isMoved) {
        return fileHandlers.getStrategy(type).copyFileTo(sourcePath, targetPath, fileId, userId, isMoved);
    }

    @Override
    public Files renameFile(String sourceName, String targetName, long userId, long fileId) {
        Files buffer = fileHandlers.getStrategy(type).renameFile(sourceName,targetName,userId,fileId);
        if (buffer!= null){
            filesDao.queryById(fileId);
            buffer.setName(targetName);
        }
        return null;
    }
}
