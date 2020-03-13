package cn.bestsort.cloud_disk.controller;

import cn.bestsort.cloud_disk.model.entity.ShareMap;
import cn.bestsort.cloud_disk.service.ShareMapService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 分享-文件关系表(ShareMap)表控制层
 *
 * @author bestsort
 * @since 2020-03-12 09:33:33
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