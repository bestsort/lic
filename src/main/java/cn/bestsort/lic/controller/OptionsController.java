package cn.bestsort.lic.controller;

import cn.bestsort.lic.model.entity.Options;
import cn.bestsort.lic.service.OptionsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * 用户配置表(Options)表控制层
 *
 * @author bestsort
 * @since 2020-03-12 09:33:33
 */
@RestController
@RequestMapping("options")
public class OptionsController {
    /**
     * 服务对象
     */
    @Resource
    private OptionsService optionsService;

    @PutMapping("/test")
    public List<Options> test(){
        return null;
    }
    private List<Options> generatorOptions(int begin, int end){
        List list = new LinkedList();
        for (int i = begin;i <= end;i++ ){
            Options buffer = new Options();
            buffer.setOptionKey("key-" + i);
            buffer.setOptionValue("value-" + i);
            list.add(buffer);
        }
        return list;
    }
}