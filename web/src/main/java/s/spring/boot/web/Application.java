package s.spring.boot.web;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chensihong
 * @version 1.0
 * @date 2024/3/9 14:01
 */

@SpringBootApplication(scanBasePackages = "s.spring.boot")
@MapperScan(basePackages = "s.spring.boot.dal.mapper")
@EnableMethodCache(proxyTargetClass = true, basePackages = "s.spring.boot.service.cache")
@EnableCreateCacheAnnotation
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
