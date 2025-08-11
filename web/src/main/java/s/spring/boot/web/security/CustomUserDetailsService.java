package s.spring.boot.web.security;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import s.spring.boot.dal.entity.UserInfoEntity;
import s.spring.boot.dal.service.UserInfoDalService;

/**
 * @author chensihong
 * @version 1.0
 * @date 2024/3/19 23:37
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoDalService userInfoDalService;

    @Override
    public TokenUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfoEntity userInfo = userInfoDalService.getOne(new LambdaQueryWrapper<UserInfoEntity>()
                .eq(UserInfoEntity::getUsername, username));

        if(userInfo == null){
            throw new AuthenticationCredentialsNotFoundException("用户不存在");
        }
        TokenUserDetails tokenUserDetails = new TokenUserDetails();
//        tokenUserDetails.setUserInfo(userInfo);
        return tokenUserDetails;
    }
}
