package cn.bestsort.cloud_disk.file.service;

import cn.bestsort.cloud_disk.file.entity.Files;

import java.util.List;

/**
 * TODO
 *
 * @author bestsort
 * @version 1.0
 * @date 2/29/20 2:47 PM
 */
public interface CloudDiskFileSystemInterface extends BaseFileSystemInterface {
    /**
     * 查看某目录下所有文件信息
     * @param path 路径
     * @param userId 用户id（用于校验访问权限）
     * @return 文件列表
     */
    List<Files> listFiles(String path, long userId);

}
