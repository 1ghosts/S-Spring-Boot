package s.spring.boot.biz.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author  chensihong
 * @date  2023/7/18 20:40
 * @version 1.0
 *
 *
 */
@Service
@Slf4j
public class RedisTemplateService {

    @Autowired
    private RedisTemplate redisTemplate;


    public <K, V> void set(K key, V value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public <K, V> boolean setIfAbsent(K key, V value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    public <K, V> void setExpire(K key, V value, long seconds) {
        redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
    }

    public <K, V> V get(K key) {
        Object o = redisTemplate.opsForValue().get(key);
        if (o != null) {
            return (V) o;
        }
        return null;
    }

    public <K> boolean existKey(K key) {
        return redisTemplate.hasKey(key);
    }


    /**
     * spring 集成的无法获取 del 返回值
     *
     * @param key
     * @return
     */
    public <K> boolean del(K key) {
        return redisTemplate.delete(key);
    }

    /**
     * 删除多个key
     *
     * @param keys
     * @param <K>
     * @return
     */
    public <K> Long dels(Collection<K> keys) {
        return redisTemplate.delete(keys);
    }

    /**
     * 这则表达式匹配所有key结果
     *
     * @param pattern
     */
    public <V> Set<V> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }


}
