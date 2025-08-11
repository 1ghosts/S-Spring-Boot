package s.spring.boot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import s.spring.boot.common.result.ApiResult;
import s.spring.boot.biz.redis.RedisTemplateService;

import java.util.Map;

/**
 * @author chensihong
 * @version 1.0
 * @date 2024/3/9 14:03
 */
@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisTemplateService redisTemplateService;

    @PostMapping("/set")
    public ApiResult set(@RequestBody Map<String, String> params) {
        redisTemplateService.set(params.get("id"), params.get("name"));
        return ApiResult.ofSuccess();
    }

    @GetMapping("/get/{id}")
    public ApiResult get(@PathVariable String id){
        return ApiResult.ofSuccess(redisTemplateService.get(id));
    }


}
