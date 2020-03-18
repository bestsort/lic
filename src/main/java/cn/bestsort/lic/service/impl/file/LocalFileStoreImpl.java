package cn.bestsort.lic.service.impl.file;

import cn.bestsort.lic.model.entity.Files;
import cn.bestsort.lic.model.enums.FileStoreType;
import cn.bestsort.lic.model.enums.propertys.FilePropertyEnum;
import cn.bestsort.lic.model.enums.propertys.LocalFileProperties;
import cn.bestsort.lic.service.FileStoreInterface;
import cn.bestsort.lic.utils.ConvertUtil;
import cn.bestsort.lic.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

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
@Component("local")
public class LocalFileStoreImpl extends AbstractFileStore implements FileStoreInterface {
    @Override
    public String makeDir(String path, String dirName, long userId) {
        File dir = new File(FileUtil.unionPath(FileUtil.getAbsolutePath(path),dirName));
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

            File sourcesFile = new File(FileUtil.unionAbsolutePath(fileInfo.getRealPath(),fileInfo.getName()));
            File targetFile = new File(FileUtil.unionAbsolutePath(targetPath, fileInfo.getName()));

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
    public Files rename(Files fileInfo, String target){
        fileInfo.setName(target);
        filesRepository.saveAndFlush(fileInfo);
        return fileInfo;
    }

    @Override
    Files upload(MultipartFile file, Long targetDirId) {

        Files fileInfo = ConvertUtil.multipartFile2Files(
            file,
            this,
            targetDirId == 0 ?
                null :
                filesRepository.findById(targetDirId).orElse(null)
        );
        filesRepository.save(fileInfo);
        return fileInfo;
    }

    @Override
    public FileStoreType getStoreType() {
        return FileStoreType.DEFAULT;
    }

    @Override
    public Class<? extends FilePropertyEnum> getType() {
        return LocalFileProperties.class;
    }
}
