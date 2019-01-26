/*-
 * This software is the property of:
 *
 * World Fuel Services Corporation.
 * Copyright (c) 2017 World Fuel Services Corporation.
 *
 * It may not be copied, distributed or modified, in part or in whole,
 * by any means whatsoever, without the explicit written permission of World Fuel Services Corporation.
 */
package org.arun.springoauth.config;

import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * SecurityContextUtils is used to get username and roles to set created by, last updated by fields.
 */
@Component
public class SecurityContextUtils {

  private static final Logger LOGGER = LoggerFactory.getLogger(SecurityContextUtils.class);

  private static final String ANONYMOUS = "anonymous";

  private SecurityContextUtils() {
  }

  public static String getUserName() {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    Authentication authentication = securityContext.getAuthentication();
    String username = ANONYMOUS;

    if (null != authentication) {
      if (authentication.getPrincipal() instanceof UserDetails) {
        UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
        username = springSecurityUser.getUsername();

      } else if (authentication.getPrincipal() instanceof String) {
        username = (String) authentication.getPrincipal();

      } else {
        LOGGER.debug("User details not found in Security Context");
      }
    } else {
      LOGGER.debug("Request not authenticated, hence no user name available");
    }

    return username;
  }

  public static Set<String> getUserRoles() {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    Authentication authentication = securityContext.getAuthentication();
    Set<String> roles = new HashSet<>();

    if (null != authentication) {
      authentication.getAuthorities()
          .forEach(e -> roles.add(e.getAuthority()));
    }
    return roles;
  }
}
