package s.spring.boot.common.exception;

public class AccountLoginException extends RuntimeException {

    private Integer code;

    private String message;

    public AccountLoginException(String message)
    {
        this.message = message;
    }

    public AccountLoginException(String message, Integer code)
    {
        this.message = message;
        this.code = code;
    }

    public AccountLoginException(String message, Throwable e)
    {
        super(message, e);
        this.message = message;
    }

    @Override
    public String getMessage()
    {
        return message;
    }

    public Integer getCode()
    {
        return code;
    }
}