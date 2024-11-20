package ma.srmanager.srjwt.coreapi.jwt;

public class JWTUtil {

    public static final String JWT_SECRET = "L@@k00d@@y";
    public static final String JWT_HEADER_NAME = "Authorization";
    public static final String JWT_PREFEX_COMPTE = "COMPTE_";
    public static final String JWT_HEADER_PREFIX = "Bearer ";
    //2mn
    //public static final Long EXPIRE_ACCESS_TOKEN = 120000L;

    //120j
    public static final Long EXPIRE_ACCESS_TOKEN = 120 * 24 * 3600 * 1000L;

    //360j
    public static final Long EXPIRE_REFRESH_TOKEN = 360 * 24 * 3600 * 1000L;

    public static final String JWT_CLAIMS_ROLES = "roles";
    public static final String JWT_CLAIMS_COMPTE = "compte";
    public static final String JWT_LIBELLE_ACCESS_TOKEN = "accessToken";
    public static final String JWT_LIBELLE_REFRESH_TOKEN = "refreshToken";
    public static final String JWT_CONTENT_TYPE = "application/json";
    public static final String PATH_REFRESH_TOKEN = "/refreshtoken";
    public static final String JWT_LIBELLE_ERROR_MESSAGE = "Error message";

    }
