package cn.bestsort.cloud_disk.file.service;

import cn.bestsort.cloud_disk.file.entity.Files;

/**
 * 默认文件操作
 * @author bestsort
 * @since 2020-02-29 14:34:33
 */
public interface BaseFileSystemInterface {
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
     * 将文件从 sourcePath 移动到 targetPath
     * @param sourcePath 移动前路径
     * @param targetPath 移动后
     * @param userId 移动者id
     * @param fileId 文件/目录id
     * @param isMove 移动/复制
     * @return 移动后路径
     */
    String copyFileTo(String sourcePath, String targetPath, long fileId, long userId, boolean isMove);


    /**
     * 重命名文件
     * @param sourceName 命名前
     * @param targetName 命名后
     * @param userId 操作者id
     * @param fileId 文件id
     * @return 重命名结果
     */
    Files renameFile(String sourceName, String targetName, long userId, long fileId);
}