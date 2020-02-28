package cn.bestsort.cloud_disk.message.service;

import cn.bestsort.cloud_disk.message.entity.Message;
import java.util.List;

/**
 * 消息表(Message)表服务接口
 *
 * @author bestsort
 * @since 2020-02-28 10:27:17
 */
public interface MessageService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Message queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Message> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param message 实例对象
     * @return 实例对象
     */
    Message insert(Message message);

    /**
     * 修改数据
     *
     * @param message 实例对象
     * @return 实例对象
     */
    Message update(Message message);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}