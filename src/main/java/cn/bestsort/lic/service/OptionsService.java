package cn.bestsort.lic.service;

import java.util.Map;

/**
 * 用户配置表(Options)表服务接口
 *
 * @author bestsort
 * @since 2020-03-12 09:33:33
 */
public interface OptionsService  {

    /**
     * 通过 key 查询单条数据
     *
     * @param key 键
     * @return    实例对象
     */
    String queryValueByKey(String key);

    /**
     * 通过 key 查询 value, 若未找到则返回 defaultOptionValue
     * @param optionKey key
     * @param defaultOptionValue 默认值
     * @return value
     */
    String queryValueByKeyOrDefault(String optionKey, Object defaultOptionValue);

    /**
     * 新增/更新缓存
     * @param optionKey    键
     * @param optionValue  值
     */
    void inertOrUpdate(String optionKey, String optionValue);

    /**
     * 插入/更新集合中所有K-V
     * @param set 集合
     */
    void insertOrUpdateBySet(Map<String, String> set);

    /**
     * 删除
     * @param key    要删除的 key
     * @return       是否成功
     */
    boolean deleteByKey(String key);

}