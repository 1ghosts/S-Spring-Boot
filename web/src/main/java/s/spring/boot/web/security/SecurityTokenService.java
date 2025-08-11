package s.spring.boot.web.security;


import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import s.spring.boot.common.constant.Constants;
import s.spring.boot.common.entity.dto.AuthUserInfoDTO;
import s.spring.boot.common.exception.AccountLoginException;
import s.spring.boot.common.utils.SecurityTokenUtils;
import s.spring.boot.common.utils.ServletUtils;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Component
@Slf4j
public class SecurityTokenService {

    /**
     * 令牌秘钥
     */
    @Value("#{'${token.secret}'.getBytes('UTF-8')}")
    private byte[] secret;

    /**
     * 令牌有效期（默认30分钟）
     */
    @Value("${token.expireTime}")
    private int expireTime;


    @Autowired
    private StringRedisTemplate redisTemplate;

//    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public TokenUserDetails getTokenUserDetails(HttpServletRequest request) {
        // 1、想从请求头中获取携带的令牌
        String token = SecurityTokenUtils.getToken(request);
        return parseTokenStr(token);
    }


    public AuthUserInfoDTO getSystemUserByToken() {
        TokenUserDetails TokenUserDetails = getTokenUserDetails(ServletUtils.getRequest());
        if (TokenUserDetails == null) {
            throw new AccountLoginException("重新登入");
        }
        return TokenUserDetails.getUser();
    }


    public TokenUserDetails parseTokenStr(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        try {
            // 1、解析密钥串
            Claims claims = parseToken(token);
            // 2、解析对应的权限以及用户信息
            String uuid = (String) claims.get(Constants.LOGIN_USER_KEY);
            Object tenantIdObj = claims.get(Constants.LOGIN_TENANT_KEY);

            // 3、校验token session
//            String tokenSession = getTokenSession(uuid, tenantId);
//            if (!token.equals(tokenSession)) {
//                log.warn("token session已失效 {}", uuid);
//                return null;
//            }
            // 4、redis中获取对应的登入信息
            String userKey = getTokenKey(uuid);
            String loginUserStr = redisTemplate.opsForValue().get(userKey);
            if (!StringUtils.isEmpty(loginUserStr)) {
                return JSONObject.parseObject(loginUserStr, TokenUserDetails.class);
            }
        } catch (Exception e) {
            log.error("解析 token异常 {} {}", token, e.getMessage());
        }
        return null;
    }


    /**
     * 设置用户身份信息
     */
    public void setSystemUser(TokenUserDetails userToken) {
        if (userToken != null && StringUtils.isNotEmpty(userToken.getToken())) {
            refreshToken(userToken);
        }
    }

    /**
     * 删除用户身份信息
     */
    public void delLoginUserToken(String token) {

        String userKey = getTokenKey(token);
        redisTemplate.delete(userKey);
    }


    /**
     * 创建令牌
     * 这里直接使用userid作为key，会出现缓存数据冲突问题；当用户A切换到租户1，用户B再次登入切换租户2；导致用户A数据出现问题；
     * 解决：用户B登入后，让用户A的token session失效；
     *
     * 之前把uuid修改userid作为key的原因：是要做踢出用户会话的功能；
     *
     * @param userToken 用户信息
     * @return 令牌
     */
    public String createToken(TokenUserDetails userToken) {
        String userid = userToken.getUser().getUserId().toString();
        userToken.setToken(userid);

        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.LOGIN_USER_KEY, userid);
        String token = createToken(claims);

        refreshToken(userToken);
        return token;
    }


    /**
     * 验证令牌有效期，相差不足10分钟，自动刷新缓存
     *
     * @param userToken
     * @return 令牌
     */
    public void verifyTokenTime(TokenUserDetails userToken) {
        long expireTime = userToken.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= Constants.MILLIS_MINUTE_TEN) {
            refreshToken(userToken);
        }
    }

    /**
     * 刷新令牌有效期
     *
     * @param loginUser 登录信息
     */
    public void refreshToken(TokenUserDetails loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * Constants.MILLIS_MINUTE);
        // 根据uuid将loginUser缓存
        String userKey = getTokenKey(loginUser.getToken());
        redisTemplate.opsForValue().set(userKey, JSONObject.toJSONString(loginUser), expireTime, TimeUnit.MINUTES);
    }




    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String createToken(Map<String, Object> claims) {
        long nowTime = System.currentTimeMillis();
        String token = Jwts.builder().claims(claims)
                .expiration(new Date(nowTime + expireTime))
                .signWith(Keys.hmacShaKeyFor(secret)).compact();
        return token;
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret))
                .build()
                .parseEncryptedClaims(token)
                .getPayload();
    }

    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.getSubject();
    }


    private String getTokenKey(String uuid) {
        return Constants.LOGIN_TOKEN_KEY + uuid;
    }



    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword     真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    /**
     * 判断token是否已经失效
     */
    public boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     */
    public Date getExpiredDateFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.getExpiration();
    }

}
