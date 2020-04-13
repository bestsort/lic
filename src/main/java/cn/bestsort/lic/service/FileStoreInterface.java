package cn.bestsort.lic.service;

import cn.bestsort.lic.model.entity.Files;
import cn.bestsort.lic.model.enums.FileStoreType;
import cn.bestsort.lic.model.enums.propertys.FilePropertyEnum;
import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件存储系统接口
 * @author bestsort
 * @version 1.0
 * @date 2/29/20 2:47 PM
 */
public interface FileStoreInterface {
    /**
     * 在指定路径下创建目录
     * @param path 路径
     * @param dirname 目录名
     * @param userId 创建者ID
     * @return 创建结果
     */
    String makeDir(String path, String dirname, long userId);


    /**
     * 删除文件/目录
     * @param fileId 文件/目录对应的id
     * @param userId 删除者id
     * @param isDir 是否是目录
     * @return 删除结果
     */
    boolean deleteFile(long fileId, long userId, boolean isDir);


    /**
     * 将文件移动到 targetPath
     * @param sourcePath 移动前路径
     * @param targetPath 移动后路径()
     * @param isMove 移动/复制
     * @return 移动后路径
     */
    String copyFileTo(String sourcePath, String targetPath, boolean isMove);


    /**
     * 重命名文件
     * @param sourceName 命名前
     * @param targetName 命名后
     * @return           重命名后绝对路径
     */
    String renameFile(String sourceName, String targetName);

    /**
     * 文件上传
     * @param file          需要上传的文件
     * @param targetDirId   上传目录的id
     * @return              上传后的文件信息
     */
    Files uploadFile(@NonNull MultipartFile file, @NonNull Long targetDirId);
    /**
     * 每个具体实现类都需要实现该方法表示自己所支持的文件系统
     * @return FileStoreType 支持的文件系统
     */
    FileStoreType getStoreType();

    /**
     * 每个具体实现类都需要实现该方法表明自己所需要的配置字段
     * @return enum 需要的配置类
     */
    abstract Class<? extends FilePropertyEnum> getType();
    /**
     * 获取文件真实路径
     * @param parentDir 目标文件夹绝对路径
     * @param fileName  文件名
     * @return          文件全路径(绝对路径)
     */
    String getRealPath(String parentDir, String fileName);

    /**
     * 获取文件真实路径(根目录下)
     * @param fileName 文件名
     * @return         文件全路径
     */
    default String getRealPath(String fileName){
        return getRealPath("/", fileName);
    }
}
