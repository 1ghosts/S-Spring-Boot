package s.spring.boot.common.result;

import lombok.Data;
import s.spring.boot.common.exception.ResCodeEnum;

/**
 * @author chensihong
 * @version 1.0
 * @date 2024/3/11 16:38
 */
@Data
public class ApiResult<T> {

    private int code;

    private String errorMsg;

    private T data;

    public ApiResult() {
        this.code = ResCodeEnum.SUCCESS.getCode();
    }

    public ApiResult(T data) {
        this.code = ResCodeEnum.SUCCESS.getCode();
        this.data = data;
    }

    public ApiResult(ResCodeEnum errorCode) {
        this.code = errorCode.getCode();
        this.errorMsg = errorCode.getMsg();
    }

    public ApiResult(ResCodeEnum errorCode, String errorMsg) {
        this.code = errorCode.getCode();
        this.errorMsg = errorMsg;
    }

    public ApiResult(int errorCode, String errorMsg) {
        this.code = errorCode;
        this.errorMsg = errorMsg;
    }

    public boolean isSuccess() {
        return ResCodeEnum.SUCCESS.getCode() == this.getCode();
    }

    public static <Data> ApiResult<Data> ofSuccess(Data t) {
        return new ApiResult<>(t);
    }

    public static ApiResult<?> ofSuccess() {
        return new ApiResult<>();
    }

    public static ApiResult<?>  ofError(int errorCode, String errorMsg) {
        return new ApiResult<>(errorCode, errorMsg);
    }

    public static ApiResult<?>  ofError(ResCodeEnum resCodeEnum) {
        return new ApiResult<>(resCodeEnum);
    }

    public static ApiResult<?>  ofError(ResCodeEnum resCodeEnum, String errorMsg) {
        return new ApiResult<>(resCodeEnum, errorMsg);
    }
}
