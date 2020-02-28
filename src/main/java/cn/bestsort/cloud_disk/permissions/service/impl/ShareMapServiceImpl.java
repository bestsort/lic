package cn.bestsort.cloud_disk.permissions.service.impl;

import cn.bestsort.cloud_disk.permissions.entity.ShareMap;
import cn.bestsort.cloud_disk.permissions.dao.ShareMapDao;
import cn.bestsort.cloud_disk.permissions.service.ShareMapService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 分享-文件关系表(ShareMap)表服务实现类
 *
 * @author bestsort
 * @since 2020-02-28 10:27:31
 */
@Service("shareMapService")
public class ShareMapServiceImpl implements ShareMapService {
    @Resource
    private ShareMapDao shareMapDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ShareMap queryById(Long id) {
        return this.shareMapDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<ShareMap> queryAllByLimit(int offset, int limit) {
        return this.shareMapDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param shareMap 实例对象
     * @return 实例对象
     */
    @Override
    public ShareMap insert(ShareMap shareMap) {
        this.shareMapDao.insert(shareMap);
        return shareMap;
    }

    /**
     * 修改数据
     *
     * @param shareMap 实例对象
     * @return 实例对象
     */
    @Override
    public ShareMap update(ShareMap shareMap) {
        this.shareMapDao.update(shareMap);
        return this.queryById(shareMap.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.shareMapDao.deleteById(id) > 0;
    }
}