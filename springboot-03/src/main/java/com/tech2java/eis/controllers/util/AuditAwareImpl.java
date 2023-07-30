package com.tech2java.eis.controllers.util;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		
		//Optional.ofNullable(SecurityContextHolder.getcontext().getAuthentication().getName());
		
		//if we implement spring security then we can get logged-in user from SpringSecurityContext
		return Optional.of("System");
	}

}
