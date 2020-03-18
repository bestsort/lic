package cn.bestsort.lic.service.impl;

import cn.bestsort.lic.handler.CacheStoreHandler;
import cn.bestsort.lic.handler.FileStoreHandler;
import cn.bestsort.lic.model.entity.Files;
import cn.bestsort.lic.model.enums.FileStoreType;
import cn.bestsort.lic.repository.FilesRepository;
import cn.bestsort.lic.service.CloudDiskFileSystemInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    private CacheStoreHandler cacheStoreHandler;
    @Resource
    private FileStoreHandler fileStoreHandler;
    private FileStoreType type = FileStoreType.DEFAULT;
    @Override
    public List<Files> listFiles(String path) {
        return null;
    }

    @Override
    public String makeDir(String path, String dirname, long userId) {
        return fileStoreHandler.fetchFileStore().makeDir(path, dirname, userId);
    }

    @Override
    public boolean deleteFile(long fileId, long userId, boolean isDir) {
        return fileStoreHandler.fetchFileStore().deleteFile(fileId, userId, isDir);
    }

    @Override
    public String copyFileTo(String sourcePath, String targetPath,long fileId, long userId, boolean isMoved) {
        return fileStoreHandler.fetchFileStore().copyFileTo(sourcePath, targetPath, fileId, userId, isMoved);
    }

    @Override
    public Files renameFile(String sourceName, String targetName, long userId, long fileId) {
        Files buffer = fileStoreHandler.fetchFileStore().renameFile(sourceName,targetName,userId,fileId);
        if (buffer!= null){
            filesRepository.findById(fileId);
            buffer.setName(targetName);
        }
        return null;
    }

    @Override
    public Files uploadFile(MultipartFile file, Long targetDirId) {
        return fileStoreHandler.
            fetchFileStore().
            uploadFile(file, targetDirId);
    }

}
