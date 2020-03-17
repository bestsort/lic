package cn.bestsort.dubai.repository;

import cn.bestsort.dubai.model.entity.Options;
import cn.bestsort.dubai.repository.base.BaseRepository;

/**
 * TODO
 *
 * @author bestsort
 * @version 1.0
 * @date 2020/3/16 上午10:47
 */
public interface OptionsRepository extends BaseRepository<Options, Long> {
    Options findByOptionKey(String key);

}
