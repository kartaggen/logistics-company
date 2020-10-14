package com.f97808.logisticscompany.service;

import com.f97808.logisticscompany.entity.User;
import com.f97808.logisticscompany.jpa.UserRepository;
import com.f97808.logisticscompany.model.LoginUserDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl implements UserLoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final UserDetailsServiceNoSql userDetailsServiceNoSql;

    UserLoginServiceImpl(UserRepository userRepository, PasswordEncoder encoder, UserDetailsServiceNoSql userDetailsServiceNoSql) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.userDetailsServiceNoSql = userDetailsServiceNoSql;
    }

    @Override
    public boolean loginUser(LoginUserDto user) {
        User loginUser = userRepository.findByUsername(user.getUsername());
        if(loginUser== null)return false;
        if (encoder.matches(user.getPassword(), loginUser.getPassword())) {
            userDetailsServiceNoSql.loadUserByUsername(user.getUsername());
            return true;
        } else return false;
    }

}
