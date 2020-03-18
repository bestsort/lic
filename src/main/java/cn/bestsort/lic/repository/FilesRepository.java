package cn.bestsort.lic.repository;

import cn.bestsort.lic.model.entity.Files;
import cn.bestsort.lic.repository.base.BaseRepository;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * @author bestsort
 * @version 1.0
 * @date 2020/3/16 上午10:34
 */
public interface FilesRepository extends BaseRepository<Files, Long> {
    /**
     * 根据名字获取文件夹
     * @param name 文件夹名
     * @param parentId 是否是文件夹
     * @return 文件夹列表
     */
    Files findByNameAndParentId(@NonNull String name, @NonNull Long parentId);
}
