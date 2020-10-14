package com.f97808.logisticscompany.service;

import com.f97808.logisticscompany.entity.User;
import com.f97808.logisticscompany.jpa.UserRepository;
import com.f97808.logisticscompany.model.Authorities;
import com.f97808.logisticscompany.model.UserDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    UserRegistrationServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        addAdminIfNone();
    }

    @Override
    public void createUser(UserDto user) {
        User newUser = new User(user.getUsername(),
                encoder.encode(user.getPassword()),
                user.getEmail(),
                user.getFirstname(),
                user.getLastname(),
                new Date(),
                Authorities.CLIENT);
        userRepository.save(newUser);
    }

    @Override
    public void addAdminIfNone() {
        if (userRepository.findByRole("ADMIN").isEmpty()) {
            User user = new User("Admin", encoder.encode("temppass"), "admin@lc.com", "admin", "admin", new Date(), "ADMIN");
            System.out.println("---------------------------------ADDING ADMIN{username=admin  &  password=temppass}---------------------------------");
            userRepository.save(user);
        }
    }

}
