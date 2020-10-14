package com.f97808.logisticscompany.validation;

import com.f97808.logisticscompany.entity.User;
import com.f97808.logisticscompany.jpa.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, String> {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    PasswordMatchValidator(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public boolean isValid(String oldPass, ConstraintValidatorContext context) {
        List<User> admins = userRepository.findByRole("ADMIN");
        if (admins.size() == 1) {
            User admin = admins.get(0);
            return oldPass != null && encoder.matches(oldPass, admin.getPassword());
        }
        return false;
    }

}
