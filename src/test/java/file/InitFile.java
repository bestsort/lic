/*
package file;

import cn.bestsort.cloud_disk.Main;
import cn.bestsort.cloud_disk.dao.FilesDao;
import cn.bestsort.cloud_disk.dao.UserDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.net.URL;

*/
/**
 * TODO
 *
 * @author bestsort
 * @version 1.0
 * @date 3/6/20 5:12 PM
 *//*



@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InitFile {
    @LocalServerPort
    private int port;

    private URL base;


    @Resource
    FilesDao filesDao;
    @Resource
    UserDao userDao;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Before
    public void setUp() throws Exception {
        String url = String.format("http://localhost:%d/", port);
        System.out.println(String.format("port is : [%d]", port));
        this.base = new URL(url);
    }
}*/
