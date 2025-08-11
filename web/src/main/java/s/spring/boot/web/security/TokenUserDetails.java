package s.spring.boot.web.security;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import s.spring.boot.common.entity.dto.AuthUserInfoDTO;

import java.util.Collection;
import java.util.List;

/**
 * @author chensihong
 * @version 1.0
 * @date 2024/3/20 17:34
 */
@Data
public class TokenUserDetails implements UserDetails {
    private String token;
    private long loginTime;
    private long expireTime;

    private AuthUserInfoDTO user;
    /**
     * 权限列表
     */
    private List<UserGrantedAuthority> authorityList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList;
    }

    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
