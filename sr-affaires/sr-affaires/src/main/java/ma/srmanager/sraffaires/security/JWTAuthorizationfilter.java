package ma.srmanager.sraffaires.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import ma.srmanager.coreapi.jwt.JWTUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class JWTAuthorizationfilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        //System.out.println("doFilterInternal");

        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
        response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin,Access-Control-Allow-Credentials,Authorization");
        response.addHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,PATCH");

        //System.out.println("doFilterInternal");
        //System.out.println(request.getRequestURI());
        //System.out.println(request.getServletPath());

        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else if (request.getRequestURI().equals("/login")
                || request.getServletPath().equals(JWTUtil.PATH_REFRESH_TOKEN)) {
            filterChain.doFilter(request, response);
            return;
        } else {

            String authorizationToken = request.getHeader(JWTUtil.JWT_HEADER_NAME);

            if (authorizationToken != null && authorizationToken.startsWith(JWTUtil.JWT_HEADER_PREFIX)) {
                try {
                    String token = authorizationToken.substring(JWTUtil.JWT_HEADER_PREFIX.length());
                    Algorithm algo1 = Algorithm.HMAC256(JWTUtil.JWT_SECRET);
                    JWTVerifier jwtVerifier = JWT.require(algo1).build();
                    DecodedJWT decodedJWT = jwtVerifier.verify(token);
                    String username = decodedJWT.getSubject();
                    String[] roles = decodedJWT.getClaim(JWTUtil.JWT_CLAIMS_ROLES).asArray(String.class);

//                    System.out.println("username        "+username);
//                    System.out.println("roles           "+roles);


                    Collection<GrantedAuthority> authorities = new ArrayList<>();
                    for (String role : roles) {
                        authorities.add(new SimpleGrantedAuthority(role));
                    }
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(username, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);

                } catch (Exception e) {
                    response.setHeader(JWTUtil.JWT_LIBELLE_ERROR_MESSAGE, e.getMessage());
                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
                }
            } else {
                filterChain.doFilter(request, response);
            }
        }

    }
}
