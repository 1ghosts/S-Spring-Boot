package s.spring.boot.common.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author chensihong
 * @version 1.0
 * @date 2024/3/12 09:49
 */
@Data
public class UserInfoDTO implements Serializable {
    private Integer age;

    /**
     * 别名
     */
    private String alias;

    /**
     * 用户名
     */
    private String username;

    /**
     * 号码
     */
    private String mobile;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    /**
     * 状态
     */
    private String state;
}
