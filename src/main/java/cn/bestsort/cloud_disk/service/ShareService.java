package cn.bestsort.cloud_disk.service;

import cn.bestsort.cloud_disk.model.entity.Share;
import java.util.List;

/**
 * (Share)表服务接口
 *
 * @author bestsort
 * @since 2020-03-12 09:33:33
 */
public interface ShareService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Share queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Share> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param share 实例对象
     * @return 实例对象
     */
    Share insert(Share share);

    /**
     * 修改数据
     *
     * @param share 实例对象
     * @return 实例对象
     */
    Share update(Share share);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}