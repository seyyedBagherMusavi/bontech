package co.bontech.exam.security;

import co.bontech.exam.domain.User;
import co.bontech.exam.repository.UserRepository;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final UserRepository userRepository;

    public DomainUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String userName) {
        log.debug("Authenticating {}", userName);
        return userRepository
            .findOneByUserName(userName)
            .map(this::createSpringSecurityUser)
            .orElseThrow(() -> new UsernameNotFoundException("User " + userName + " was not found in the database"));
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser( User user) {
        List<SimpleGrantedAuthority> grantedAuthorities =
                List.of(new SimpleGrantedAuthority(user.getAuthority().name()));
        MyPrincipal myPrincipal = new MyPrincipal(user.getUserName(),
                user.getPassword(), grantedAuthorities);
        myPrincipal.setUserId(user.getId());
        return myPrincipal;
    }
}
