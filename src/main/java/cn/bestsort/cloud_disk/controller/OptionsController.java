package cn.bestsort.cloud_disk.controller;

import cn.bestsort.cloud_disk.model.entity.Options;
import cn.bestsort.cloud_disk.service.OptionsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户配置表(Options)表控制层
 *
 * @author bestsort
 * @since 2020-03-12 09:33:33
 */
@RestController
@RequestMapping("options")
public class OptionsController {
    /**
     * 服务对象
     */
    @Resource
    private OptionsService optionsService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Options selectOne(Long id) {
        return this.optionsService.queryById(id);
    }

}