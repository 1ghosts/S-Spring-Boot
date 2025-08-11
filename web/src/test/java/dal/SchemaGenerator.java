package dal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.google.common.collect.ImmutableMap;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author chensihong
 * @version 1.0
 * @date 2024/3/11 14:03
 */
public class SchemaGenerator {
    /**
     * 数据源配置
     */
    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://127.0.0.1:3306/s-spring-boot?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=Asia/Shanghai", "root", "12345678");
//    .Builder("jdbc:mysql://127.0.0.1:3306/enterprise-workflow?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=Asia/Shanghai", "root", "12345678");


    public static void main(String[] args) {
        presetGenerator();

//        inputGenerator();
    }

    /**
     * 写死
     */
    public static void presetGenerator(){
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                // 全局配置
                .globalConfig((builder) -> builder.author("chensihong")
                        .outputDir("/Users/chensihong/private/S-Spring-Boot/web/src/main/java"))
                // 包配置
                .packageConfig((builder) -> builder.parent("s.spring.boot.dal")
                        .entity("entity")
                        .service("service")
                        .serviceImpl("service.impl")
                        .mapper("mapper")
                        .xml("mapper.xml")
                        .controller("controller")
//                        .pathInfo(ImmutableMap.of(OutputFile.controller, "/Users/chensihong/private/S-Spring-Boot/web/src/main/java/s/spring/boot/web/controller"))
                )
                // 策略配置
                .strategyConfig((builder) -> builder.addInclude(getTables("t_user_info"))
//                        .enableCapitalMode()
                        .enableSkipView()
//                        .disableSqlFilter()
//                        .likeTable(new LikeTable("USER"))
                        //包含哪些表
//                        .addInclude("t_simple")
                        //排除哪些表
//                        .addExclude("t_xx")
                        //去掉表前缀
                        .addTablePrefix("t_")
                        //去掉表后缀
//                        .addFieldSuffix("_flag")

                        //controller
                        .controllerBuilder()
                        .enableFileOverride()
                        .enableRestStyle()
                        .enableHyphenStyle()
                        .formatFileName("%sController")
                        //service
                        .serviceBuilder()
                        .enableFileOverride()
//                        .superServiceClass(IService.class)
                        .superServiceImplClass(ServiceImpl.class)
                        .formatServiceFileName("%sDalService")
                        .formatServiceImplFileName("%sDalServiceImpl")
                        //mapper
                        .mapperBuilder()
                        .enableFileOverride()
                        .superClass(BaseMapper.class)
//                        .enableMapperAnnotation()
                        .enableBaseResultMap()
                        .enableBaseColumnList()
//                        .cache(MyMapperCache.class)
                        .formatMapperFileName("%sMapper")
                        .formatXmlFileName("%sMapperXml")

                        //entity
                        .entityBuilder()
                        .enableFileOverride()
                        .disableSerialVersionUID()
//                        .enableChainModel()
                        .enableLombok()
                        .enableRemoveIsPrefix()
                        .enableTableFieldAnnotation()
//                        https://baomidou.com/pages/49cc81/#activerecord-%E6%A8%A1%E5%BC%8F
//                        .enableActiveRecord()
                        .versionColumnName("version")
                        //.versionPropertyName("version")
//                        .logicDeleteColumnName("deleted")
                        //.logicDeletePropertyName("deleteFlag")
                        .naming(NamingStrategy.underline_to_camel)
                        .columnNaming(NamingStrategy.underline_to_camel)
//                        .addSuperEntityColumns("id", "created_by", "created_time", "updated_by", "updated_time")
//                        .addIgnoreColumns("age")
//                        .addTableFills(new Column("create_time", FieldFill.INSERT))
//                        .addTableFills(new Property("updateTime", FieldFill.INSERT_UPDATE))
                        .idType(IdType.AUTO)
                        .formatFileName("%sEntity").build())
                //自定义模板
                // controller设置为空，则不生成
//                .templateConfig((builder -> builder.controller("")))
                /*
                    模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker
                   .templateEngine(new BeetlTemplateEngine())
                   .templateEngine(new FreemarkerTemplateEngine())
                 */
                .templateEngine(new FreemarkerTemplateEngine())
//                todo 待处理dto
//                .injectionConfig(consumer -> {
////                    Map<String, String> customFile = new HashMap<>();
//                    // DTO
////                    customFile.put("DTO.java", "/templates/entityDTO.java.ftl");
//                    consumer.customFile(new CustomFile.Builder().filePath("/Users/chensihong/private/S-Spring-Boot/service/src/main/java")
//                                    .packageName("s.spring.boot.service.dto")
//                            .fileName("DTO.java").templatePath("templates/entityDTO.java.ftl").build());
//                })
                .execute();
    }

    /**
     * scanner 输入
     * 不同输出包路径时，或多人操作时不用修改，运行时输入即可
     */
    public static void inputGenerator(){
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                // 全局配置
                .globalConfig((scanner, builder) -> builder.author(scanner.apply("请输入作者名称？"))
                        .outputDir("/Users/chensihong/private/S-Spring-Boot/web/src/main/java"))
                // 包配置
                .packageConfig((scanner, builder) -> builder.parent(scanner.apply("请输入包名？"))
                        .entity("entity")
                        .service("service")
                        .serviceImpl("service.impl")
                        .mapper("mapper")
                        .xml("mapper.xml")
                        .controller("controller")
//                        .pathInfo(ImmutableMap.of(OutputFile.controller, "/Users/chensihong/private/S-Spring-Boot/web/src/main/java/s/spring/boot/web/controller"))
                )
                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请输入表名？")))
//                        .enableCapitalMode()
                        .enableSkipView()
//                        .disableSqlFilter()
//                        .likeTable(new LikeTable("USER"))
                        //包含哪些表
//                        .addInclude("t_simple")
                        //排除哪些表
//                        .addExclude("t_xx")
                        //去掉表前缀
                        .addTablePrefix("t_")
                        //去掉表后缀
//                        .addFieldSuffix("_flag")

                        //controller
                        .controllerBuilder()
                        .enableFileOverride()
                        .enableRestStyle()
                        .enableHyphenStyle()
                        .formatFileName("%sController")
                        //service
                        .serviceBuilder()
                        .enableFileOverride()
//                        .superServiceClass(IService.class)
                        .superServiceImplClass(ServiceImpl.class)
                        .formatServiceFileName("%sDalService")
                        .formatServiceImplFileName("%sDalServiceImpl")
                        //mapper
                        .mapperBuilder()
                        .enableFileOverride()
                        .superClass(BaseMapper.class)
//                        .enableMapperAnnotation()
                        .enableBaseResultMap()
                        .enableBaseColumnList()
//                        .cache(MyMapperCache.class)
                        .formatMapperFileName("%sMapper")
                        .formatXmlFileName("%sMapperXml")

                        //entity
                        .entityBuilder()
                        .enableFileOverride()
//                        .disableSerialVersionUID()
//                        .enableChainModel()
                        .enableLombok()
                        .enableRemoveIsPrefix()
                        .enableTableFieldAnnotation()
//                        https://baomidou.com/pages/49cc81/#activerecord-%E6%A8%A1%E5%BC%8F
//                        .enableActiveRecord()
                        .versionColumnName("version")
                        //.versionPropertyName("version")
//                        .logicDeleteColumnName("deleted")
                        //.logicDeletePropertyName("deleteFlag")
                        .naming(NamingStrategy.underline_to_camel)
                        .columnNaming(NamingStrategy.underline_to_camel)
//                        .addSuperEntityColumns("id", "created_by", "created_time", "updated_by", "updated_time")
//                        .addIgnoreColumns("age")
//                        .addTableFills(new Column("create_time", FieldFill.INSERT))
//                        .addTableFills(new Property("updateTime", FieldFill.INSERT_UPDATE))
                        .idType(IdType.AUTO)
                        .formatFileName("%sEntity").build())
                //自定义模板
                // controller设置为空，则不生成
//                .templateConfig((builder -> builder.controller("")))
                /*
                    模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker
                   .templateEngine(new BeetlTemplateEngine())
                   .templateEngine(new FreemarkerTemplateEngine())
                 */
                .templateEngine(new FreemarkerTemplateEngine())
//                todo 待处理dto
//                .injectionConfig(consumer -> {
////                    Map<String, String> customFile = new HashMap<>();
//                    // DTO
////                    customFile.put("DTO.java", "/templates/entityDTO.java.ftl");
//                    consumer.customFile(new CustomFile.Builder().filePath("/Users/chensihong/private/S-Spring-Boot/service/src/main/java")
//                                    .packageName("s.spring.boot.service.dto")
//                            .fileName("DTO.java").templatePath("templates/entityDTO.java.ftl").build());
//                })
                .execute();
    }

    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }



    /**
     * 执行数据库脚本
     */
    private static void initDataSource(DataSourceConfig dataSourceConfig) throws SQLException {
        Connection conn = dataSourceConfig.getConn();
        InputStream inputStream = SchemaGenerator.class.getResourceAsStream("/sql/init.sql");
        ScriptRunner scriptRunner = new ScriptRunner(conn);
        scriptRunner.setAutoCommit(true);
        scriptRunner.runScript(new InputStreamReader(inputStream));
        conn.close();
    }

    /**
     * 注入配置
     */
    private static InjectionConfig.Builder injectionConfig() {
        // 测试自定义输出文件之前注入操作，该操作再执行生成代码前 debug 查看
        return new InjectionConfig.Builder().beforeOutputFile((tableInfo, objectMap) -> {
            System.out.println("tableInfo: " + tableInfo.getEntityName() + " objectMap: " + objectMap.size());
        });
    }
}
