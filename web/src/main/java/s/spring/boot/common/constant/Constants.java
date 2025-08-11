package s.spring.boot.common.constant;

public class Constants {

    private Constants() {
    }

    /**
     * 所有权限标识
     */
    public static final String ALL_PERMISSION = "*:*:*";

    /**
     * 管理员角色权限标识
     */
    public static final String SUPER_ADMIN = "admin";

    public static final String ROLE_DELIMETER = ",";

    public static final String PERMISSION_DELIMETER = ",";


    public static final long MILLIS_MINUTE = 60 * 1000L;

    public static final long MILLIS_MINUTE_TEN = 10 * MILLIS_MINUTE;


    /**
     * 令牌前缀
     */
    public static final String LOGIN_USER_KEY = "login_user_key";
    public static final String LOGIN_TENANT_KEY = "login_tenant_key";

    /**
     * redis 缓存前缀
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";


    public static final String LOGIN_TOKEN_SESSION_KEY = "login_tokens:session:";
}