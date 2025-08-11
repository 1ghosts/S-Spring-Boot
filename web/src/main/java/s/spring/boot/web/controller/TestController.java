package s.spring.boot.web.controller;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import s.spring.boot.common.result.ApiResult;
import s.spring.boot.common.entity.req.DateFormatReq;
import s.spring.boot.common.entity.resp.DateFormatResp;

/**
 * @author chensihong
 * @version 1.0
 * @date 2024/3/9 14:03
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello world!";
    }

    @PostMapping("/testDateFormart")
    public ApiResult<DateFormatResp> testDateFormart(@RequestBody DateFormatReq req) {
        return ApiResult.ofSuccess(BeanUtil.copyProperties(req, DateFormatResp.class));
    }
}
