package com.thinhda.spring.grpc.core.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * Created by thinhda.
 * Date: 2019-07-24
 */

@Slf4j
//
@Component
public class ServerAuthenticateProvider implements AuthenticationProvider {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) {
        // User is authenticated
        return new UsernamePasswordAuthenticationToken("thinhda", "", Collections.singleton(new SimpleGrantedAuthority("ADMIN")));
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }


    @Getter
    @NoArgsConstructor
    @ToString
    private static class VerifyResponse {
        private boolean status;
    }
}
