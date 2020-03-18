package cn.bestsort.lic.service.impl.file;

import cn.bestsort.lic.model.entity.Files;
import cn.bestsort.lic.model.enums.FileStoreType;
import cn.bestsort.lic.model.enums.propertys.AliOssProperties;
import cn.bestsort.lic.model.enums.propertys.FilePropertyEnum;
import cn.bestsort.lic.service.FileStoreInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
public class AliOssFileStoreImpl extends AbstractFileStore implements FileStoreInterface {

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
    public Files uploadFile(MultipartFile file, Long targetDirId) {
        return null;
    }

    @Override
    Files upload(MultipartFile file, Long targetDirId) {
        return null;
    }


    @Override
    public FileStoreType getStoreType() {
        return FileStoreType.ALI_OSS;
    }

    @Override
    public String getRealPath(String parentDir, String fileName) {
        return null;
    }

    @Override
    Files rename(Files fileInfo, String target) {
        return null;
    }

    @Override
    public Class<? extends FilePropertyEnum> getType() {
        return AliOssProperties.class;
    }
}
