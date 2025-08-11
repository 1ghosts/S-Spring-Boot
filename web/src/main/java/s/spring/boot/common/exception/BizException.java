package s.spring.boot.common.exception;

import lombok.Data;

/**
 * Created on 2020-12-14.
 *
 * @author chensihong
 */
@Data
public class BizException extends RuntimeException {

    private Integer errorCode;

    private String errorMsg;

    public BizException(ResCodeEnum resCodeEnum) {
        super(resCodeEnum.getMsg());
        this.errorCode = resCodeEnum.getCode();
        this.errorMsg = resCodeEnum.getMsg();
    }

    public BizException(Integer errorCode, String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }
}
