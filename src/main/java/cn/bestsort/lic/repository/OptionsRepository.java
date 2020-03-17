package cn.bestsort.lic.repository;

import cn.bestsort.lic.model.entity.Options;
import cn.bestsort.lic.repository.base.BaseRepository;

/**
 * TODO
 *
 * @author bestsort
 * @version 1.0
 * @date 2020/3/16 上午10:47
 */
public interface OptionsRepository extends BaseRepository<Options, Long> {
    /**
     * 根据 key 查找 option
     * @param key not be null
     * @return option
     */
    Options findByOptionKey(String key);

}
