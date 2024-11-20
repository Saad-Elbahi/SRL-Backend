package ma.srmanager.srjwt.coreapi.jwt;

import java.util.Date;

public interface SecurityParams {
     static final String JWT_HEADER_NAME="Authorization";
     static final String SECRET="PA@@22154@@";
     static final Date EXPIRATION=new Date(System.currentTimeMillis()+10*24*3600*1000);
     static final String JWT_HEADER_PREFIX="Bearer ";
     static final String JWT_CLAIMS_NAME ="roles";
}
