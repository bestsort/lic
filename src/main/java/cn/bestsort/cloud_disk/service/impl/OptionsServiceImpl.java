package cn.bestsort.cloud_disk.service.impl;

import cn.bestsort.cloud_disk.model.entity.Options;
import cn.bestsort.cloud_disk.dao.OptionsDao;
import cn.bestsort.cloud_disk.service.OptionsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户配置表(Options)表服务实现类
 *
 * @author bestsort
 * @since 2020-03-12 09:33:33
 */
@Service("optionsService")
public class OptionsServiceImpl implements OptionsService {
    @Resource
    private OptionsDao optionsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Options queryById(Long id) {
        return this.optionsDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Options> queryAllByLimit(int offset, int limit) {
        return this.optionsDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param options 实例对象
     * @return 实例对象
     */
    @Override
    public Options insert(Options options) {
        this.optionsDao.insert(options);
        return options;
    }

    /**
     * 修改数据
     *
     * @param options 实例对象
     * @return 实例对象
     */
    @Override
    public Options update(Options options) {
        this.optionsDao.update(options);
        return this.queryById(options.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.optionsDao.deleteById(id) > 0;
    }
}