package ma.srmanager.srjwt.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import ma.srmanager.srjwt.entities.AppUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        // System.out.println("attemptAuthentication");

        try {
            AppUser appUser = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword());

            //System.out.println(appUser.getUsername());
            //System.out.println(appUser.getPassword());

            return authenticationManager.authenticate(authenticationToken);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


        /*String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println(username);
        System.out.println(password);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authenticationToken);*/
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        //System.out.println("successfulAuthentication");
        //System.out.println(authResult.getPrincipal());


        User user = (User) authResult.getPrincipal();

//        System.out.println("Username :"+user.getUsername());
//        System.out.println("Roles :"+user.getAuthorities());

        Algorithm algo1 = Algorithm.HMAC256(JwtTools.JWT_SECRET);

        String jwtAccessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtTools.EXPIRE_ACCESS_TOKEN))
                .withIssuer(request.getRequestURL().toString())
                .withClaim(JwtTools.JWT_CLAIMS_ROLES, user.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .sign(algo1);


        String jwtRefreshToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtTools.EXPIRE_REFRESH_TOKEN))
                .withIssuer(request.getRequestURL().toString())
                .sign(algo1);

        Map<String, String> idToken = new HashMap<>();
        idToken.put(JwtTools.JWT_LIBELLE_ACCESS_TOKEN, jwtAccessToken);
        idToken.put(JwtTools.JWT_LIBELLE_REFRESH_TOKEN, jwtRefreshToken);
        response.setContentType(JwtTools.JWT_CONTENT_TYPE);

        new ObjectMapper().writeValue(response.getOutputStream(), idToken);
        //response.setHeader("Authorization", jwtAccessToken);


        /*
         .withClaim(JWTUtil.JWT_CLAIMS_COMPTE, user.getAuthorities().stream()
                        .filter(ga -> ga.getAuthority().startsWith(JWTUtil.JWT_PREFEX_COMPTE))
                        .map(ga -> ga.getAuthority().substring(JWTUtil.JWT_PREFEX_COMPTE.length()))
                        .findFirst().orElseGet(null))
         */
    }
}
