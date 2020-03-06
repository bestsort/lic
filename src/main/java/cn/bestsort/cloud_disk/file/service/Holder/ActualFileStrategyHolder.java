package cn.bestsort.cloud_disk.file.service.Holder;

import cn.bestsort.cloud_disk.file.service.ActualFileSystemInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 根据参数选择不同的文件管理方式
 * @author bestsort
 * @version 1.0
 * @date 3/1/20 3:40 PM
 */
@ConfigurationProperties(prefix="file-system")
@Component
@Slf4j
public class ActualFileStrategyHolder {

    @Autowired
    private Map<String, ActualFileSystemInterface> actualFileInterfaceMap;

    private static String DEFAULT_STRATEGY = "local";

    private String storage = DEFAULT_STRATEGY;

    public ActualFileSystemInterface getStrategy(){
        ActualFileSystemInterface actualFileInterface = actualFileInterfaceMap.get(storage);
        if (actualFileInterface == null){
            return actualFileInterfaceMap.get(DEFAULT_STRATEGY);
        }
        return actualFileInterface;
    }

    public String getStorage(){
        return storage;
    }

    public void setStorage(String storage){
        this.storage = storage;
    }
}
