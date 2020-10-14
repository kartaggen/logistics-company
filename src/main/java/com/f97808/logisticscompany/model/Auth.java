package com.f97808.logisticscompany.model;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Auth implements Authentication {

    private final String name;
    private boolean authenticated;
    private final UserPrincipal userPrincipal;
    private final Set<GrantedAuthority> authorities = new HashSet<>();

    public Auth(UserPrincipal userPrincipal) {
        this.userPrincipal = userPrincipal;
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.userPrincipal.getUser().getRole()));
        authenticated = true;
        name = userPrincipal.getUsername();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return userPrincipal.getUser();
    }

    @Override
    public Object getDetails() {
        return userPrincipal.getUser();
    }

    @Override
    public Object getPrincipal() {
        return userPrincipal;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return name;
    }
}
