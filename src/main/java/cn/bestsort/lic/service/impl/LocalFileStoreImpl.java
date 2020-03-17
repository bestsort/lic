package cn.bestsort.lic.service.impl;

import cn.bestsort.lic.exception.ItemExtendException;
import cn.bestsort.lic.model.entity.Files;
import cn.bestsort.lic.model.enums.FileStoreType;
import cn.bestsort.lic.repository.FilesRepository;
import cn.bestsort.lic.service.FileStoreInterface;
import cn.bestsort.lic.utils.FilePathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * @author bestsort
 * @version 1.0
 * @date 2/29/20 3:01 PM
 */

@Slf4j
@Lazy
@Service("local")
public class LocalFileStoreImpl implements FileStoreInterface {
    @Resource
    private FilesRepository filesRepository;

    @Override
    public String makeDir(String path, String dirName, long userId) {
        File dir = new File(FilePathUtil.unionPath(FilePathUtil.getAbsolutePath(path),dirName));
        if(dir.mkdirs()){
            return path + dirName;
        };
        return null;
    }

    @Override
    public boolean deleteFile(long fileId, long userId, boolean isDir) {
        Optional<Files> fileInfo = filesRepository.findById(fileId);
        if (!fileInfo.isPresent()){
            throw new NullPointerException();
        }
        File file = Paths.get(fileInfo.get().getRealPath(),fileInfo.get().getName()).toFile();
        Assert.notNull(file, "目标文件未找到");
        return file.delete();
    }

    @Override
    public String copyFileTo(String sourcePath, String targetPath,long fileId, long userId, boolean isMoved) {
        try {
            Files fileInfo = filesRepository.findById(fileId).get();

            File sourcesFile = new File(FilePathUtil.unionAbsolutePath(fileInfo.getRealPath(),fileInfo.getName()));
            File targetFile = new File(FilePathUtil.unionAbsolutePath(targetPath, fileInfo.getName()));

            FileChannel source = new FileInputStream(sourcesFile).getChannel();
            FileChannel target = new FileOutputStream(targetFile).getChannel();
            try {
                target.transferFrom(source, 0, source.size());
                if (isMoved) {
                    sourcesFile.delete();
                }
            }finally {
                source.close();
                target.close();
            }
        } catch (IOException e) {
            log.error("用户{}尝试将文件 [{}] 移动到 [{}] 失败,原因:{}", userId, sourcePath, targetPath, e.getMessage());
            return null;
        }
        return targetPath;
    }

    @Override
    public Files renameFile(String sourceName, String targetName, long userId, long fileId) {
        log.info("用户(id:{}) 正尝试将文件(id:{}) [{}] 重命名为 [{}]",userId,fileId, sourceName, targetName);
        if (sourceName.equals(targetName)){
            return null;
        }
        Files fileInfo = filesRepository.findById(fileId).get();
        if (fileInfo == null){
            throw new NullPointerException("未查询到此文件信息, 请检查或刷新后重试");
        }
        if (!fileInfo.getName().equals(sourceName)){
            throw new IllegalArgumentException("请检查选中的文件, 或刷新后重试");
        }

        String path = FilePathUtil.tryAddPathEndCharset(fileInfo.getRealPath());
        File source = new File(path + fileInfo.getName());
        File target = new File(path + targetName);
        if (target.exists()){
            throw new ItemExtendException("该文件名已经存在");
        }

        //TODO 事务控制
        if (source.renameTo(target)){
            fileInfo.setName(targetName);
            filesRepository.saveAndFlush(fileInfo);
            log.info("位于[{}]的文件 [{}] 被成功重命名, 新文件名为:[{}]", path, sourceName, targetName);
        }
        return fileInfo;
    }

    @Override
    public FileStoreType getStoreType() {
        return FileStoreType.DEFAULT;
    }
}
