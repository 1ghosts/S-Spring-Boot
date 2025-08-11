package s.spring.boot.common.exception;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * Created on 2020-12-11.
 *
 * @author chensihong
 */
@Getter
public enum ResCodeEnum {
    /**
     * 成功
     */
    SUCCESS(0, "success"),
    PARAM_ERROR(10000, "参数异常"),
    THIRD_ERROR(20000, "第三方服务异常"),

    USER_NOT_EXIST(30000, "用户不存在"),
    USER_SESSION_EXPIRED(30001, "用户会话过期，请重新登录"),
    USER_TOKEN_ILLEGAL(30002, "非法token，请重新登录，刷新token"),
    USER_PASSWORD_ERROR(30003, "用户密码错误"),
    CREATE_TOKEN_ERROR(30004, "token生成失败，请稍后重试"),

    ;


    private Integer code;
    private String msg;

    ResCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BizException ofBizException() {
        return new BizException(this);
    }

    public BizException ofBizException(final String errMsg) {
        return StringUtils.isEmpty(errMsg) ? this.ofBizException() : new BizException(this.code, errMsg);
    }

    public BizException ofBizException(final String errMsg, Object... dynamicParams) {
        if (StringUtils.isEmpty(errMsg)) {
            return this.ofBizException();
        }
        if (null == dynamicParams) {
            return this.ofBizException(errMsg);
        }
        return new BizException(this.code, String.format(errMsg, dynamicParams));
    }

}
