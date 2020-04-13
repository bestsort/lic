package cn.bestsort.lic.bridge;

import cn.bestsort.lic.model.entity.Files;
import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 对外暴露的文件操作
 * @author bestsort
 * @version 1.0
 * @date 2/29/20 2:47 PM
 */
public interface FileStoreBridgeInterface {
    /**
     * 查看某目录下所有文件信息
     * @param path 路径
     * @return 文件列表
     */
    List<Files> listFiles(String path);

    /**
     * 获取文件详情
     * @param fileId long
     * @return       文件详情
     */
    Files getFile(Long fileId);

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
     * @param targetDirId      目标文件夹ID
     * @param fileId        文件/目录id
     * @param isMove        移动/复制
     * @param actual        对文件实体的操作(true -> 同步移动, false -> 只修改数据库内文件映射信息)
     * @return              移动后路径
     */
    String copyFileTo(long fileId, long targetDirId, boolean isMove, boolean actual);


    /**
     * 重命名文件
     * @param targetName    命名后
     * @param fileId        文件id
     * @return              重命名结果
     */
    String renameFile(String targetName, long fileId);

    /**
     * 文件上传
     * @param file          需要上传的文件
     * @param targetDirId   上传目录的id
     * @return              上传后的文件信息
     */
    Files uploadFile(@NonNull MultipartFile file, @NonNull Long targetDirId);
}
