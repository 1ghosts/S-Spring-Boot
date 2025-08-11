package s.spring.boot.web.config;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;


/**
 * Swagger Config
 *
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {


    @Value("${swagger.enable:false}")
    private Boolean enable;

    @Bean
    public Docket createRestApi() {
//        List<SecurityScheme> schemeList = new ArrayList<>();
//        schemeList.add(new ApiKey(HttpHeaders.AUTHORIZATION, "Authorization", "header"));
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enable)
                .apiInfo(apiInfo())
//                .securitySchemes(schemeList)
//                .securityContexts(securityContexts())
                .groupName("S-Spring-Boot")
                .select()
                //解析包下的接口
                .apis(RequestHandlerSelectors.basePackage("s.spring.boot"))
                //解析@Api注解接口
//                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())

                .build();
    }

    private List<SecurityContext> securityContexts() {
        return Lists.newArrayList(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        //正则式过滤,此处是所有非login开头的接口都需要认证
                        .forPaths(PathSelectors.regex("^(?!login).*$"))
                        .build()
        );
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "认证权限");
        return Lists.newArrayList(
                new SecurityReference("Authorization", new AuthorizationScope[]{authorizationScope}));
    }

    /**
     * 重写basePackage方法，使能够实现多包访问，复制贴上去
     */
    public static Predicate<RequestHandler> basePackages(final String basePackage) {
        return input -> declaringClass(input).transform(handlerPackage(basePackage)).or(true);
    }

    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage)     {
        return input -> {
            // 循环判断匹配
            for (String strPackage : basePackage.split(",")) {
                boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                if (isMatch) {
                    return true;
                }
            }
            return false;
        };
    }

    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
        return Optional.fromNullable(input.getReturnType().getErasedType());
    }




    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("【这是项目文档名称】")
                .version("【这是版本号-1.0】")
                .description("这是描述")
                .build();
    }

}
