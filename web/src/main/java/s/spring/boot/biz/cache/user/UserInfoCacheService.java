package s.spring.boot.biz.cache.user;

import com.alicp.jetcache.anno.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s.spring.boot.common.utils.BeanCopyUtil;
import s.spring.boot.dal.entity.UserInfoEntity;
import s.spring.boot.common.entity.dto.UserInfoDTO;
import s.spring.boot.dal.service.UserInfoDalService;

import java.util.concurrent.TimeUnit;

/**
 * @author chensihong
 * @version 1.0
 * @date 2024/3/12 22:25
 */
@Slf4j
@Service
public class UserInfoCacheService {
    @Autowired
    private UserInfoDalService userInfoDalService;

    @Cached(keyConvertor = KeyConvertor.NONE, serialPolicy = SerialPolicy.JAVA, cacheType = CacheType.REMOTE,
            enabled = true,
            name = "user:info:", key = "#id",
            cacheNullValue = false, timeUnit = TimeUnit.HOURS, expire = 1)
    public UserInfoDTO get(String id) {
        UserInfoEntity one = userInfoDalService.getOne(new LambdaQueryWrapper<UserInfoEntity>().eq(UserInfoEntity::getId, id));
        log.info("get {}", id);
        return BeanCopyUtil.copy(one, UserInfoDTO.class);
    }

    @CacheInvalidate(
            name = "user:info:", key = "#id")
    public String removeGet(String id) {
        log.info("remove {}", id);
        return "success";
    }

}
