/*
package option;

import cn.bestsort.cloud_disk.dao.OptionsDao;
import cn.bestsort.cloud_disk.model.entity.Options;
import cn.bestsort.cloud_disk.service.impl.OptionsServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

*/
/**
 * @author bestsort
 * @version 1.0
 * @date 3/14/20 11:24 AM
 *//*



@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootConfiguration
public class OptionSqlTest {

    @Autowired
    OptionsDao dao;
    @Test
    public void insertListTest(){
        List<Options> list = generatorOptions(5);
        dao.insertOrUpdateByOptions(list);
    }

    private List<Options> generatorOptions(int n){
        return generatorOptions(1, n, 1);
    }
    private List<Options> generatorOptions(int begin, int end, int id){
        List list = new LinkedList();
        for (int i = begin;i <= end;i++ ){
            Options buffer = new Options();
            buffer.setOptionKey("key-" + i);
            buffer.setOptionValue("value-" + i);
            buffer.setId(1L * i);
            list.add(buffer);
        }
        return list;
    }
}
*/
