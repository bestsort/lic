package cn.bestsort.cloud_disk.service;

import cn.bestsort.cloud_disk.model.entity.ShareMap;
import java.util.List;

/**
 * 分享-文件关系表(ShareMap)表服务接口
 *
 * @author bestsort
 * @since 2020-03-12 09:33:33
 */
public interface ShareMapService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ShareMap queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ShareMap> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param shareMap 实例对象
     * @return 实例对象
     */
    ShareMap insert(ShareMap shareMap);

    /**
     * 修改数据
     *
     * @param shareMap 实例对象
     * @return 实例对象
     */
    ShareMap update(ShareMap shareMap);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}