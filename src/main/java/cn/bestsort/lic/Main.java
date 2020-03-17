package cn.bestsort.dubai;

import cn.bestsort.dubai.config.properties.DuBaiProperties;
import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author bestsort
 * @version 1.0
 * @date 2/23/20 9:20 AM
 */
@SpringBootApplication
@EnableSwaggerBootstrapUI
@EnableConfigurationProperties(DuBaiProperties.class)
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
