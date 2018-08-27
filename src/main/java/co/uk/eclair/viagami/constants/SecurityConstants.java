package co.uk.eclair.viagami.constants;

/**
 * Created by ${Eclair} on 8/26/2018.
 */
public class SecurityConstants {
    public static final String SECRET = "AAAAB3NzaC1yc2EAAAADAQABAAABAQC66bEMaDzN0QS878tJuYHRyN1nfbollxre";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/sign-up";
    public static final String LOGIN = "/users/login";
}
