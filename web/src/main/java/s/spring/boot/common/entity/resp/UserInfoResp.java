package s.spring.boot.common.entity.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author chensihong
 * @version 1.0
 * @date 2024/3/12 09:49
 */
@Data
public class UserInfoResp implements Serializable {
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

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参jackson-annotations注解
    private LocalDateTime createTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参jackson-annotations注解
    private LocalDateTime updateTime;

    /**
     * 状态
     */
    private String state;
}
