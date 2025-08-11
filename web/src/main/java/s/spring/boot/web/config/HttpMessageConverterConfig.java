package s.spring.boot.web.config;

import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.nio.charset.Charset;

/**
 * @author chensihong
 * @version 1.0
 * @date 2024/3/14 23:44
 */
@Configuration
public class HttpMessageConverterConfig {

    @Bean
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverterEx() {
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter=new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig =new FastJsonConfig();
        fastJsonConfig.setCharset(Charset.defaultCharset());
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteMapNullValue);
        fastJsonConfig.setFeatures(Feature.OrderedField);
        SerializeConfig serializeConfig = SerializeConfig.globalInstance;
        serializeConfig.put(Long.class, ToStringSerializer.instance);
        serializeConfig.put(Long.TYPE, ToStringSerializer.instance);
        fastJsonConfig.setSerializeConfig(serializeConfig);

        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        return fastJsonHttpMessageConverter;
    }
}
