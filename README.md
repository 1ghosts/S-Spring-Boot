# S-Spring-Boot
SpringBoot框架搭建的项目基底

## 启动
    java -jar -Djasypt.encryptor.password=S-Spring-Boot

## jasypt
[项目引用](https://github.com/ulisesbocchio/jasypt-spring-boot?tab=readme-ov-file)

    密码：S-Spring-Boot，特殊字符需要转义，如：123\@\!
    启动参数增加-Djasypt.encryptor.password=S-Spring-Boot
    关闭：注释web模块jasypt依赖，perperties中加密字段改成原文即可
        <dependency>
            <groupId>com.github.ulisesbocchio</groupId>
            <artifactId>jasypt-spring-boot-starter</artifactId>
            <version>3.0.5</version>
        </dependency>
    使用插件加解密：
        加密：mvn jasypt:encrypt-value -Djasypt.encryptor.password="the password" -Djasypt.plugin.value="theValueYouWantToEncrypt"
        解密：mvn jasypt:decrypt-value -Djasypt.encryptor.password="the password" -Djasypt.plugin.value="DbG1GppXOsFa2G69PnmADvQFI3esceEhJYbaEIKCcEO5C85JEqGAhfcjFMGnoRFf"