package cn.bestsort.lic.handler;

import cn.bestsort.lic.model.enums.FileStoreType;
import cn.bestsort.lic.service.ActualFileSystemInterface;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashMap;

/**
 * @author bestsort
 * @version 1.0
 * @date 3/13/20 8:25 AM
 */

@Component
public class FileStoreHandler {

    private HashMap<FileStoreType, ActualFileSystemInterface> fileHandlers = new HashMap<>();
    private static FileStoreType STRATEGY = FileStoreType.DEFAULT;


    public FileStoreHandler(ApplicationContext applicationContext){
        addFileHandlers(applicationContext.getBeansOfType(ActualFileSystemInterface.class).values());
    }


    /**
     * @param fileHandlers can be null
     */
    @NonNull
    public void addFileHandlers(@Nullable Collection<ActualFileSystemInterface> fileHandlers){
        if (!CollectionUtils.isEmpty(fileHandlers)){
            for (ActualFileSystemInterface fileHandler : fileHandlers) {
                this.fileHandlers.put(fileHandler.getType(), fileHandler);
            }
        }
    }

    public static String getStrategy(){
        return STRATEGY.toString();
    }
    public static void setStrategy(FileStoreType strategy){
        Assert.notNull(strategy, "文件系统类型不可为空");
        STRATEGY = strategy;
    }

    public ActualFileSystemInterface fetchFileStore(){
        return  fileHandlers.getOrDefault(STRATEGY.toString(),fileHandlers.get(FileStoreType.DEFAULT));
    }
}
