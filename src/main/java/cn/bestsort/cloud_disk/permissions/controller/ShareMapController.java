package cn.bestsort.cloud_disk.permissions.controller;

import cn.bestsort.cloud_disk.permissions.entity.ShareMap;
import cn.bestsort.cloud_disk.permissions.service.ShareMapService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 分享-文件关系表(ShareMap)表控制层
 *
 * @author bestsort
 * @since 2020-02-28 10:27:31
 */
@RestController
@RequestMapping("shareMap")
public class ShareMapController {
    /**
     * 服务对象
     */
    @Resource
    private ShareMapService shareMapService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public ShareMap selectOne(Long id) {
        return this.shareMapService.queryById(id);
    }

}