package cn.bestsort.cloud_disk.file.service.impl;

import cn.bestsort.cloud_disk.file.dao.FilesDao;
import cn.bestsort.cloud_disk.file.entity.Files;
import cn.bestsort.cloud_disk.file.service.Holder.ActualFileStrategyHolder;
import cn.bestsort.cloud_disk.file.service.CloudDiskFileSystemInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.annotation.Resource;
import java.io.*;
import java.nio.channels.FileChannel;
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
public class CloudDiskFileSystemImp implements CloudDiskFileSystemInterface {

    @Resource
    private FilesDao filesDao;

    @Resource
    private ActualFileStrategyHolder actualFileStrategyHolder;

    @Override
    public List<Files> listFiles(String path, long userId) {
        return null;
    }

    @Override
    public String makeDir(String path, String dirname, long userId) {
        return actualFileStrategyHolder.getStrategy().makeDir(path, dirname, userId);
    }

    @Override
    public boolean deleteFile(long fileId, long userId, boolean isDir) {
        return actualFileStrategyHolder.getStrategy().deleteFile(fileId, userId, isDir);
    }

    @Override
    public String copyFileTo(String sourcePath, String targetPath,long fileId, long userId, boolean isMoved) {
        return actualFileStrategyHolder.getStrategy().copyFileTo(sourcePath, targetPath, fileId, userId, isMoved);
    }

    @Override
    public Files renameFile(String sourceName, String targetName, long userId, long fileId) {
        Files buffer = actualFileStrategyHolder.getStrategy().renameFile(sourceName,targetName,userId,fileId);
        if (buffer!= null){
            filesDao.queryById(fileId);
            buffer.setName(targetName);
        }
        return null;
    }
}
