package cn.bestsort.cloud_disk.dao;

import cn.bestsort.cloud_disk.model.entity.ShareMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * 分享-文件关系表(ShareMap)表数据库访问层
 *
 * @author bestsort
 * @since 2020-03-12 09:33:33
 */
 
@Mapper
public interface ShareMapDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ShareMap queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ShareMap> queryAllByLimit(int offset,int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param shareMap 实例对象
     * @return 对象列表
     */
    List<ShareMap> queryAll(ShareMap shareMap);

    /**
     * 新增数据
     *
     * @param shareMap 实例对象
     * @return 影响行数
     */
    int insert(ShareMap shareMap);

    /**
     * 修改数据
     *
     * @param shareMap 实例对象
     * @return 影响行数
     */
    int update(ShareMap shareMap);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}