package ma.srmanager.srparams.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

/************************    Authentication par session de spring       *******************/

   // @Override
    /*protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        *//***
         * Desactiver le cryptage de pwd par BCrypt
         *//*
        //auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("ADMIN","USER");
        //auth.inMemoryAuthentication().withUser("user1").password("{noop}1234").roles("USER");


        //Crypter le pwd par un Bean BCryptPasswordEncoder
        BCryptPasswordEncoder bcpe=getBcpe();
        auth.inMemoryAuthentication().withUser("admin").password(bcpe.encode("1234")).roles("ADMIN","USER");
        auth.inMemoryAuthentication().withUser("user1").password(bcpe.encode("1234")).roles("USER");
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Desactiver l'Authentification par defaut de spring
        //super.configure(http);

        //desactiver le token csrf
        http.csrf().disable();

        //Desactiver les sessions
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.headers().frameOptions().disable();

        //Gerer les Autorisations par role
        http.authorizeRequests().antMatchers("/h2-console/**","/refreshToken/**","/login/**","/v3/api-docs").permitAll();
        //http.authorizeRequests().antMatchers(HttpMethod.POST,"/users/**").hasAuthority("ADMIN");
        //http.authorizeRequests().antMatchers(HttpMethod.GET,"/users/**").hasAuthority("USER");

        //Autoriser tout le restes des requetes aux utilisateurs authentifiés
        http.authorizeRequests().anyRequest().authenticated();

        //Ajouter un filtre
        http.addFilterBefore(new JWTAuthorizationfilter(), UsernamePasswordAuthenticationFilter.class);

    }


   // @Bean
   /* public BCryptPasswordEncoder getBcpe(){
        return new BCryptPasswordEncoder();
    }*/


}
