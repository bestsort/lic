package cn.bestsort.cloud_disk.controller;

import cn.bestsort.cloud_disk.model.entity.Share;
import cn.bestsort.cloud_disk.service.ShareService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Share)表控制层
 *
 * @author bestsort
 * @since 2020-03-12 09:33:33
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