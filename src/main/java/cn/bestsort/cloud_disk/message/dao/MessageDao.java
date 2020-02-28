package cn.bestsort.cloud_disk.message.dao;

import cn.bestsort.cloud_disk.message.entity.Message;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * 消息表(Message)表数据库访问层
 *
 * @author bestsort
 * @since 2020-02-28 10:37:58
 */
 
@Mapper
public interface MessageDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Message queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Message> queryAllByLimit(int offset,int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param message 实例对象
     * @return 对象列表
     */
    List<Message> queryAll(Message message);

    /**
     * 新增数据
     *
     * @param message 实例对象
     * @return 影响行数
     */
    int insert(Message message);

    /**
     * 修改数据
     *
     * @param message 实例对象
     * @return 影响行数
     */
    int update(Message message);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}