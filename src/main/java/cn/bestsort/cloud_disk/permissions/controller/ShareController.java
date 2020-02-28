package cn.bestsort.cloud_disk.permissions.controller;

import cn.bestsort.cloud_disk.permissions.entity.Share;
import cn.bestsort.cloud_disk.permissions.service.ShareService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Share)表控制层
 *
 * @author bestsort
 * @since 2020-02-28 10:27:25
 */
@RestController
@RequestMapping("share")
public class ShareController {
    /**
     * 服务对象
     */
    @Resource
    private ShareService shareService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Share selectOne(Long id) {
        return this.shareService.queryById(id);
    }

}