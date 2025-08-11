package s.spring.boot.common.entity.req;

import lombok.Data;

/**
 * @author chensihong
 * @version 1.0
 * @date 2024/3/19 23:51
 */
@Data
public class LoginReq {
    private String username;
    private String password;
}
