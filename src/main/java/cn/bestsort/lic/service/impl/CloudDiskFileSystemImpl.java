package cn.bestsort.dubai.service.impl;

import cn.bestsort.dubai.cache.MemoryCacheStore;
import cn.bestsort.dubai.handler.FileStorageHandlers;
import cn.bestsort.dubai.model.entity.Files;
import cn.bestsort.dubai.model.enums.StorageType;
import cn.bestsort.dubai.repository.FilesRepository;
import cn.bestsort.dubai.service.CloudDiskFileSystemInterface;
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
    private FilesRepository filesRepository;
    @Resource
    private MemoryCacheStore cacheStore;
    @Resource
    private FileStorageHandlers fileHandlers;
    private StorageType type = StorageType.DEFAULT;
    @Override
    public List<Files> listFiles(String path) {
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
            filesRepository.findById(fileId);
            buffer.setName(targetName);
        }
        return null;
    }
}
