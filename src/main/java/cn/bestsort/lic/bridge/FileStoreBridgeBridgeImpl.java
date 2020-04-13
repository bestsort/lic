package cn.bestsort.lic.bridge;

import cn.bestsort.lic.exception.NotFoundException;
import cn.bestsort.lic.handler.CacheStoreHandler;
import cn.bestsort.lic.handler.FileStoreHandler;
import cn.bestsort.lic.model.entity.Files;
import cn.bestsort.lic.model.enums.FileStoreType;
import cn.bestsort.lic.repository.FilesRepository;
import cn.bestsort.lic.bridge.FileStoreBridgeInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * TODO
 *
 * @author bestsort
 * @version 1.0
 * @date 3/2/20 10:17 AM
 */
@Slf4j
@Service
public class FileStoreBridgeBridgeImpl implements FileStoreBridgeInterface {

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
    public Files getFile(Long fileId) {
        return filesRepository.findById(fileId).get();
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
    @Transactional(rollbackFor = Exception.class)
    public String copyFileTo(long fileId, long targetDir, boolean isMove, boolean actual) {
        Optional<Files> file = filesRepository.findById(fileId);
        Optional<Files> target = filesRepository.findById(targetDir);
        try{
            Assert.isTrue(file.isPresent() && target.isPresent() && target.get().isDir(),
                "文件夹下无此文件");
            Files fileInfo = file.get();
            file.get().setParentId(target.get().getId());
            if (isMove){
                filesRepository.deleteById(fileInfo.getId());
                fileInfo.setId(null);
            }
            filesRepository.save(fileInfo);

            if (actual){
                fileStoreHandler.fetchFileStore().copyFileTo(
                    fileInfo.getRealPath(),
                    target.get().getRealPath(),
                    isMove
                );
            }
            return fileInfo.getRealPath();
        }catch (NullPointerException e) {
            throw new NotFoundException("文件/文件夹未找到");
        }
    }

    @Override
    public String renameFile(String targetName, long fileId) {
        Optional<Files> filesOptional = filesRepository.findById(fileId);
        if (!filesOptional.isPresent()){
            throw new NotFoundException("目标文件未找到");
        }

        Files fileInfo = filesOptional.get();
        fileInfo.setName(targetName);

        if (!fileInfo.isDir()){
            fileInfo.setRealPath(
                fileStoreHandler.fetchFileStore(
                    fileInfo.getStoreType()).
                    renameFile(fileInfo.getRealPath(), fileInfo.getName()));
        }

        filesRepository.save(fileInfo);
        return fileInfo.getName();
    }

    @Override
    public Files uploadFile(MultipartFile file, Long targetDirId) {
        return fileStoreHandler.
            fetchFileStore().
            uploadFile(file, targetDirId);
    }

}
