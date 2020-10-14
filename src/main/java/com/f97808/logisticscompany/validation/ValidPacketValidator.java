package com.f97808.logisticscompany.validation;

import com.f97808.logisticscompany.entity.User;
import com.f97808.logisticscompany.jpa.UserRepository;
import com.f97808.logisticscompany.model.PacketDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class ValidPacketValidator implements ConstraintValidator<ValidPacket, Object> {

    private final UserRepository userRepository;

    ValidPacketValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(Object packet, ConstraintValidatorContext context) {
        int disClientId = ((PacketDto) packet).getSender();
        int recClientId = ((PacketDto) packet).getRecipient();

        Optional<User> disClient = userRepository.findById(disClientId);
        Optional<User> recClient = userRepository.findById(recClientId);

        if (disClient.isPresent() && recClient.isPresent())
            return !disClient.get().equals(recClient.get());
        else
            return false;
    }
}
