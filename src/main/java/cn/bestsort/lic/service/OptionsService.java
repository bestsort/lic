package cn.bestsort.dubai.service;

import cn.bestsort.dubai.model.entity.Options;

import java.util.List;
import java.util.Map;

/**
 * 用户配置表(Options)表服务接口
 *
 * @author bestsort
 * @since 2020-03-12 09:33:33
 */
public interface OptionsService {

    /**
     * 通过 key 查询单条数据
     *
     * @param key 键
     * @return    实例对象
     */
    Options queryByKey(String key);

    /**
     * 新增/更新缓存
     * @param key    键
     * @param value  值
     * @return 实例对象
     */
    Options inertOrUpdate(String key, String value);

    /**
     * 插入/更新集合中所有K-V
     * @param set 集合
     * @return 影响的行数
     */
    int insertOrUpdateBySet(Map<String, String> set);

    /**
     * 插入/更新所有options
     * @param options 需要新增/更新的数据
     * @return 影响的行数
     */
    int insertOrUpdateByOptions(List<Options> options);
    /**
     * @param key    要删除的 key
     * @return       是否成功
     */
    boolean deleteByKey(String key);
}