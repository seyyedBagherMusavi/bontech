package co.bontech.exam.security;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Implementation of {@link AuditorAware} based on Spring Security.
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(getCurrentUserLogin().orElse("system"));
    }
    public static Optional<String> getCurrentUserLogin() {
        MyPrincipal securityContext = (MyPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.ofNullable(securityContext.getUsername());
    }
}
