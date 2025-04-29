package com.gurpreet.loans.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

    /**
     * Returns the current auditor, which is the user currently logged in.
     * <p>
     * This implementation returns an empty Optional, indicating that there is no
     * current auditor.
     *
     * @return an empty Optional
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("LOANS_MS");
    }
}
