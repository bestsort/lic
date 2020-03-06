package file;

import cn.bestsort.cloud_disk.Main;
import cn.bestsort.cloud_disk.file.utils.FilePathUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

/**
 * TODO
 *
 * @author bestsort
 * @version 1.0
 * @date 2/29/20 3:07 PM
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DefaultActualFileTest {
    @LocalServerPort
    private int port;

    private URL base;


    @Autowired
    private TestRestTemplate testRestTemplate;

    @Before
    public void setUp() throws Exception{
        String url = String.format("http://localhost:%d/", port);
        System.out.println(String.format("port is : [%d]", port));
        this.base = new URL(url);
    }

    /**
     * 向"/test"地址发送请求，并打印返回结果
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        System.out.println(FilePathUtil.ROOT_PATH);
        System.out.println(1);
    }
}
