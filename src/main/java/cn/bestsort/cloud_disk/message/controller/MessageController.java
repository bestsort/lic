package cn.bestsort.cloud_disk.message.controller;

import cn.bestsort.cloud_disk.message.entity.Message;
import cn.bestsort.cloud_disk.message.service.MessageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 消息表(Message)表控制层
 *
 * @author bestsort
 * @since 2020-02-28 10:27:17
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