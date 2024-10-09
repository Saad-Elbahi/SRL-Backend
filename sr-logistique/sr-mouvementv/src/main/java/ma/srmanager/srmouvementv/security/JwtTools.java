package ma.srmanager.srmouvementv.security;

public  class JwtTools {
        public static final String JWT_SECRET = "L@@k00d@@y";
        public static final String JWT_HEADER_NAME = "Authorization";
        public static final String JWT_PREFEX_COMPTE = "COMPTE_";
        public static final String JWT_HEADER_PREFIX = "Bearer ";
        public static final String PATH_REFRESH_TOKEN = "/refreshtoken";
        public static final String JWT_LIBELLE_ERROR_MESSAGE = "Error message";
        public static final String JWT_CLAIMS_ROLES = "roles";
        public static final Long EXPIRE_ACCESS_TOKEN = 10368000000L;
        public static final Long EXPIRE_REFRESH_TOKEN = 31104000000L;
        public static final String JWT_CLAIMS_COMPTE = "compte";
        public static final String JWT_LIBELLE_ACCESS_TOKEN = "accessToken";
        public static final String JWT_LIBELLE_REFRESH_TOKEN = "refreshToken";
        public static final String JWT_CONTENT_TYPE = "application/json";
       
}
