package cn.bestsort.cloud_disk;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * TODO
 *
 * @author bestsort
 * @version 1.0
 * @date 2/23/20 9:20 AM
 */
@EnableSwaggerBootstrapUI

@SpringBootApplication
public class Main extends SpringBootServletInitializer {
    public static void main(String[] args) {
        System.setProperty("spring.config.additional-location", "file:${user.home}/.cloud_disk/,file:${user.home}/cloud_disk/");

        // Run application
        SpringApplication.run(Main.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        System.setProperty("spring.config.additional-location", "file:${user.home}/.halo/,file:${user.home}/halo-dev/");
        return application.sources(Main.class);
    }
}
