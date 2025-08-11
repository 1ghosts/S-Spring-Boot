package s.spring.boot.dal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author chensihong
 * @since 2024-03-20
 */
@Getter
@Setter
@TableName("t_user_info")
public class UserInfoEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 年龄
     */
    @TableField("age")
    private Integer age;

    /**
     * 别名
     */
    @TableField("alias")
    private String alias;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 号码
     */
    @TableField("mobile")
    private String mobile;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 状态
     */
    @TableField("state")
    private String state;
}
