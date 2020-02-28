package cn.bestsort.cloud_disk.file.dao;

import cn.bestsort.cloud_disk.file.entity.File;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * (File)表数据库访问层
 *
 * @author bestsort
 * @since 2020-02-28 10:37:47
 */
 
 @Mapper
public interface FileDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    File queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<File> queryAllByLimit(int offset,int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param file 实例对象
     * @return 对象列表
     */
    List<File> queryAll(File file);

    /**
     * 新增数据
     *
     * @param file 实例对象
     * @return 影响行数
     */
    int insert(File file);

    /**
     * 修改数据
     *
     * @param file 实例对象
     * @return 影响行数
     */
    int update(File file);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}