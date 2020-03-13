package cn.bestsort.cloud_disk.file.dao;

import cn.bestsort.cloud_disk.file.entity.Files;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Files)表数据库访问层
 *
 * @author bestsort
 * @since 2020-02-29 15:41:23
 */
 
@Mapper
public interface FilesDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Files queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Files> queryAllByLimit(int offset,int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param files 实例对象
     * @return 对象列表
     */
    List<Files> queryAll(Files files);

    /**
     * 新增数据
     *
     * @param files 实例对象
     * @return 影响行数
     */
    int insert(Files files);

    /**
     * 修改数据
     *
     * @param files 实例对象
     * @return 影响行数
     */
    int update(Files files);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}