package s.spring.boot.web.security.handler;


import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import s.spring.boot.common.result.ApiResult;
import s.spring.boot.common.utils.ServletUtils;
import s.spring.boot.web.security.SecurityTokenService;
import s.spring.boot.web.security.TokenUserDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义退出处理类 返回成功
 * 
 */
@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Autowired
    private SecurityTokenService tokenService;

    /**
     * 退出处理
     * 
     * @return
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        TokenUserDetails userToken = tokenService.getTokenUserDetails(request);
        if (userToken != null)
        {

            // 删除用户缓存记录
            tokenService.delLoginUserToken( userToken.getToken());
        }
        ServletUtils.renderString(response, JSON.toJSONString(ApiResult.ofSuccess()));
    }
}
