package cn.bestsort.cloud_disk.file.controller;

import cn.bestsort.cloud_disk.file.entity.File;
import cn.bestsort.cloud_disk.file.service.FileService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (File)表控制层
 *
 * @author makejava
 * @since 2020-02-23 20:04:17
 */
@RestController
@RequestMapping("file")
public class FileController {
    /**
     * 服务对象
     */
    @Resource
    private FileService fileService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public File selectOne(Long id) {
        return this.fileService.queryById(id);
    }

}