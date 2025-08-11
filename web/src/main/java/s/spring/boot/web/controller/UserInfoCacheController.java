package s.spring.boot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import s.spring.boot.common.result.ApiResult;
import s.spring.boot.biz.cache.user.UserInfoCacheService;

/**
 * @author chensihong
 * @version 1.0
 * @date 2024/3/9 14:03
 */
@RestController
@RequestMapping("/userinfo/jetcache")
public class UserInfoCacheController {
    @Autowired
    private UserInfoCacheService userInfoCacheService;

    @GetMapping("/get/{id}")
    public ApiResult get(@PathVariable String id) {
        return ApiResult.ofSuccess(userInfoCacheService.get(id));
    }

    @PostMapping("/remove/{id}")
    public ApiResult remove(@PathVariable String id) {
        return ApiResult.ofSuccess(userInfoCacheService.removeGet(id));
    }

}
