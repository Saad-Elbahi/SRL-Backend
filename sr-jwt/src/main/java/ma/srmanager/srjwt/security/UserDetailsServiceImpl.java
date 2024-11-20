package ma.srmanager.srjwt.security;

import lombok.extern.slf4j.Slf4j;
import ma.srmanager.srjwt.entities.AppUser;
import ma.srmanager.srjwt.repositories.AppUserRepository;
import ma.srmanager.srjwt.services.AccountQueryService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private AccountQueryService accountQueryService;
    private AppUserRepository appUserRepository;

    public UserDetailsServiceImpl(AccountQueryService accountQueryService, AppUserRepository appUserRepository) {
        this.accountQueryService = accountQueryService;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AppUser appUser= appUserRepository.findByUsername(username);

        Collection<GrantedAuthority> authorities=new ArrayList<>();
        appUser.getAppRoles().forEach(appRole -> {
            authorities.add(new SimpleGrantedAuthority(appRole.getRoleName()));
        });
        return new User(appUser.getUsername(),appUser.getPassword(),
                appUser.isEnabled(),appUser.isAccountNonExpired(),
                appUser.isCredentialsNonExpired(), appUser.isAccountNonLocked(),authorities);
    }
}
