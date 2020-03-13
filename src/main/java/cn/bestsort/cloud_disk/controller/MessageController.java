package cn.bestsort.cloud_disk.controller;

import cn.bestsort.cloud_disk.model.entity.Message;
import cn.bestsort.cloud_disk.service.MessageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 消息表(Message)表控制层
 *
 * @author bestsort
 * @since 2020-03-12 09:33:33
 */
@RestController
@RequestMapping("message")
public class MessageController {
    /**
     * 服务对象
     */
    @Resource
    private MessageService messageService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Message selectOne(Long id) {
        return this.messageService.queryById(id);
    }

}