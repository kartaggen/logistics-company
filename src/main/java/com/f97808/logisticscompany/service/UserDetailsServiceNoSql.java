package com.f97808.logisticscompany.service;

import com.f97808.logisticscompany.entity.User;
import com.f97808.logisticscompany.jpa.UserRepository;
import com.f97808.logisticscompany.model.Auth;
import com.f97808.logisticscompany.model.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceNoSql implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceNoSql(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        UserPrincipal up = new UserPrincipal(user);
        Auth auth = new Auth(up);
        SecurityContextHolder.getContext().setAuthentication(auth);
        return up;
    }

}