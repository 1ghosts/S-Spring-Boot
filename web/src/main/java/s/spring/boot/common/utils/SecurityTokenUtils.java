package s.spring.boot.common.utils;


import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import s.spring.boot.common.constant.TokenConstants;
import s.spring.boot.common.entity.dto.AuthUserInfoDTO;
import s.spring.boot.common.exception.AccountLoginException;
import s.spring.boot.web.security.TokenUserDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * token 处理类
 **/
@Slf4j
public class SecurityTokenUtils {

    private SecurityTokenUtils() {
    }

    /**
     * 获取请求token
     *
     * @param request
     * @return token
     */
    public static String getToken(HttpServletRequest request) {
        String token = request.getHeader(TokenConstants.HEADER);
        if (StringUtils.isNotEmpty(token)) {
            if (token.startsWith(TokenConstants.TOKEN_PREFIX)) {
                return token.replace(TokenConstants.TOKEN_PREFIX, "").trim();
            }
            log.warn("token 格式错误！{}", token);
        }
        return "";
    }

    
    /**
     * 获取用户账户
     **/
    public static Long getUserId() {
        try {
            return getUserToken().getUser().getUserId();
        } catch (Exception e) {
            throw new AccountLoginException("获取用户账户异常", HttpStatus.UNAUTHORIZED.value());
        }
    }


    /**
     * 获取用户账户
     **/
    public static String getUsername() {
        try {
            return getUserToken().getUsername();
        } catch (Exception e) {
            throw new AccountLoginException("获取用户账户异常", HttpStatus.UNAUTHORIZED.value());
        }
    }

    /**
     * 获取用户账户
     **/
    public static AuthUserInfoDTO getUserDTO() {
        try {
            return getUserToken().getUser();
        } catch (Exception e) {
            throw new AccountLoginException("获取用户账户异常", HttpStatus.UNAUTHORIZED.value());
        }
    }

    /**
     * 获取用户
     **/
    public static TokenUserDetails getUserToken() {
        try {
            return (TokenUserDetails) getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new AccountLoginException("获取用户信息异常", HttpStatus.UNAUTHORIZED.value());
        }
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }


    /**
     * 临时组建认证
     * @param systemUserDTO
     */
    public static void setTmpAuthenticationByRPC(AuthUserInfoDTO userInfoDTO){
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        TokenUserDetails TokenUserDetails = new TokenUserDetails();
        TokenUserDetails.setUser(userInfoDTO);
        AnonymousAuthenticationToken authenticationToken = new AnonymousAuthenticationToken(userInfoDTO.getUsername(),
                TokenUserDetails, Lists.newArrayList(new SimpleGrantedAuthority("tempRole")));
        securityContext.setAuthentication(authenticationToken);
        SecurityContextHolder.setContext(securityContext);
    }

    public static void clearAuthentication() {
        SecurityContextHolder.clearContext();
    }

}
