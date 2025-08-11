package s.spring.boot.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import s.spring.boot.common.result.ApiResult;
import s.spring.boot.common.entity.req.LoginReq;

/**
 * @author chensihong
 * @version 1.0
 * @date 2024/3/20 00:40
 */
@RestController
@RequestMapping("/system")
public class SystemLoginController {

    @PostMapping("/login")
    public ApiResult login(@RequestBody LoginReq req){
        return ApiResult.ofSuccess();
    }
}
