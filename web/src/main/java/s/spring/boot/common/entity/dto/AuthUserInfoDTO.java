package s.spring.boot.common.entity.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author chensihong
 * @version 1.0
 * @date 2024/3/12 09:49
 */
@Data
public class AuthUserInfoDTO implements Serializable {
    private Long userId;
    /**
     * 别名
     */
    private String alias;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 号码
     */
    private String mobile;

    /**
     * 状态
     */
    private String state;
}
