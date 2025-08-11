package s.spring.boot.web.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author chensihong
 * @version 1.0
 * @date 2024/3/20 17:46
 * 权限类
 */
public class UserGrantedAuthority implements GrantedAuthority {

    @Override
    public String getAuthority() {
        return null;
    }
}
