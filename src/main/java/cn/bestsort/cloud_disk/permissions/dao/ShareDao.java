package cn.bestsort.cloud_disk.permissions.dao;

import cn.bestsort.cloud_disk.permissions.entity.Share;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Share)表数据库访问层
 *
 * @author bestsort
 * @since 2020-02-28 10:38:04
 */
 
 @Mapper
public interface ShareDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Share queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Share> queryAllByLimit(int offset,int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param share 实例对象
     * @return 对象列表
     */
    List<Share> queryAll(Share share);

    /**
     * 新增数据
     *
     * @param share 实例对象
     * @return 影响行数
     */
    int insert(Share share);

    /**
     * 修改数据
     *
     * @param share 实例对象
     * @return 影响行数
     */
    int update(Share share);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}