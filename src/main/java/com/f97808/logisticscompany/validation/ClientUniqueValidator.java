package com.f97808.logisticscompany.validation;

import com.f97808.logisticscompany.entity.User;
import com.f97808.logisticscompany.jpa.UserRepository;
import com.f97808.logisticscompany.model.ClientDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class ClientUniqueValidator implements ConstraintValidator<ClientUnique, Object> {

    private final UserRepository userRepository;

    ClientUniqueValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(Object client, ConstraintValidatorContext context) {
        String clientUsername = ((ClientDto) client).getUsername();
        String clientEmail = ((ClientDto) client).getEmail();
        int clientId = ((ClientDto) client).getId();

        User userByUsername = userRepository.findByUsername(clientUsername);
        User userByEmail = userRepository.findByEmail(clientEmail);

        if (clientUsername == null || clientEmail == null) return false;

        if (clientId == 0) {
            return userByUsername == null && userByEmail == null;
        } else {
            Optional<User> clientById = userRepository.findById(clientId);
            if (!clientById.isPresent()) return false;
            if (userByUsername == null && userByEmail == null)
                return true;
            else if (userByUsername == null)
                return userByEmail.getId() == clientById.get().getId();
            else if (userByEmail == null)
                return userByUsername.getId() == clientById.get().getId();
            else
                return userByUsername.getId() == clientById.get().getId() &&
                        userByEmail.getId() == clientById.get().getId();
        }
    }
}
