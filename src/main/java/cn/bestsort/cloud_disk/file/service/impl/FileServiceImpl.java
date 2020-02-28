package cn.bestsort.cloud_disk.file.service.impl;

import cn.bestsort.cloud_disk.file.entity.File;
import cn.bestsort.cloud_disk.file.dao.FileDao;
import cn.bestsort.cloud_disk.file.service.FileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (File)表服务实现类
 *
 * @author makejava
 * @since 2020-02-23 20:04:17
 */
@Service("fileService")
public class FileServiceImpl implements FileService {
    @Resource
    private FileDao fileDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public File queryById(Long id) {
        return this.fileDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<File> queryAllByLimit(int offset, int limit) {
        return this.fileDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param file 实例对象
     * @return 实例对象
     */
    @Override
    public File insert(File file) {
        this.fileDao.insert(file);
        return file;
    }

    /**
     * 修改数据
     *
     * @param file 实例对象
     * @return 实例对象
     */
    @Override
    public File update(File file) {
        this.fileDao.update(file);
        return this.queryById(file.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.fileDao.deleteById(id) > 0;
    }
}