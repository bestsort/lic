package cn.bestsort.lic.service.impl.file;

import cn.bestsort.lic.exception.isExistException;
import cn.bestsort.lic.exception.NotFoundException;
import cn.bestsort.lic.handler.CacheStoreHandler;
import cn.bestsort.lic.model.entity.Files;
import cn.bestsort.lic.model.enums.UnitEnum;
import cn.bestsort.lic.model.enums.propertys.FilePropertyEnum;
import cn.bestsort.lic.repository.FilesRepository;
import cn.bestsort.lic.service.FileStoreInterface;
import cn.bestsort.lic.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * 模板方法, 对一些公共行为进行处理
 * @author bestsort
 * @version 1.0
 * @date 2020/3/18 上午9:08
 */

@Slf4j
public abstract class AbstractFileStore implements FileStoreInterface {
    @Resource
    FilesRepository filesRepository;
    @Resource
    CacheStoreHandler cacheStoreHandler;

    @Override
    public Files renameFile(String sourceName, String targetName, long userId, long fileId) {
        log.info("用户(id:{}) 正尝试将文件(id:{}) [{}] 重命名为 [{}]",userId,fileId, sourceName, targetName);
        if (sourceName.equals(targetName)){
            return null;
        }

        Optional<Files> fileInfo = filesRepository.findById(fileId);
        if (!fileInfo.isPresent()){
            throw new NullPointerException("未查询到此文件信息, 请检查或刷新后重试");
        }

        if (!fileInfo.get().getName().equals(sourceName)){
            throw new IllegalArgumentException("请检查选中的文件, 或刷新后重试");
        }
        Files targetIsExits = filesRepository.findByNameAndParentId(targetName, fileInfo.get().getParentId());
        if (targetIsExits != null){
            throw new isExistException("该文件名已经存在");
        }

        return rename(fileInfo.get(), targetName);
    }
    @Override
    public Files uploadFile(MultipartFile file, Long targetDirId){
        Assert.notNull(file, "Multipart file must not be null");
        Optional<Files> files = filesRepository.findById(targetDirId);
        // id=0 --> 上传到根目录
        if (targetDirId != 0) {
            if (!files.isPresent() || !files.get().isDir()) {
                throw new NotFoundException("文件夹未找到");
            }
        }
        if (filesRepository.findByNameAndParentId(file.getOriginalFilename(), targetDirId) != null){
            throw new isExistException("该文件名已经存在");
        }
        Files fileInfo = upload(file, targetDirId);
        if (fileInfo != null){
            log.info("文件上传成功: 文件名[{}], 文件大小[{} {}], 使用的存储模式为 [{}]",
                fileInfo.getName(),
                String.format("%.2f",FileUtil.convertSize(fileInfo.getSize(), UnitEnum.MB)),
                UnitEnum.MB,
                fileInfo.getStoreType());
        }
        return fileInfo;
    }

    abstract Files upload(MultipartFile file, Long targetDirId);


    @Override
    public String getRealPath(String parentDir, String fileName) {
        FilePropertyEnum[] filePropertyEnum = getType().getEnumConstants();
        Assert.notNull(filePropertyEnum[0], "not found file property enum");

        log.info("data dir is {}", filePropertyEnum[0].getDataDir(false));
        return Paths.get(
            cacheStoreHandler.fetchCacheStore().get(
                filePropertyEnum[0].getDataDir(false),
                filePropertyEnum[0].getDataDir(true)
            ).toString(),
            parentDir,
            fileName
        ).toString();
    }
    /**
     * 重命名
     * @param fileInfo      目标文件
     * @param target    修改后文件名
     * @return          重命名后的文件
     */
    abstract Files rename(Files fileInfo, String target);

}
