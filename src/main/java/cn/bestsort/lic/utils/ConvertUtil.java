package cn.bestsort.lic.utils;

import cn.bestsort.lic.model.entity.Files;
import cn.bestsort.lic.model.enums.UnitEnum;
import cn.bestsort.lic.service.FileStoreInterface;
import org.springframework.web.multipart.MultipartFile;

/**
 * 类型转换工具类
 * @author bestsort
 * @version 1.0
 * @date 2020/3/18 上午8:30
 */
public class ConvertUtil {

    public static Files multipartFile2Files(MultipartFile source, FileStoreInterface fileStore, Files parent){
        Files result = new Files();
        result.setName(source.getOriginalFilename());
        result.setParentId(parent == null ? 0L : parent.getId());
        result.setDir(false);
        result.setRemoved(false);
        result.setStoreType(fileStore.getStoreType());

        result.setRealPath(
            parent == null ?
            fileStore.getRealPath(source.getName()):
            fileStore.getRealPath(parent.getRealPath(), source.getName())
        );

        result.setUnit(UnitEnum.BYTE);
        result.setSize(source.getSize());
        result.setMd5("");
        return result;
    }
}
