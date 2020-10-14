package com.f97808.logisticscompany.validation;

import com.f97808.logisticscompany.entity.Employee;
import com.f97808.logisticscompany.entity.User;
import com.f97808.logisticscompany.jpa.EmployeeRepository;
import com.f97808.logisticscompany.jpa.UserRepository;
import com.f97808.logisticscompany.model.EmployeeDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class EmployeeUniqueValidator implements ConstraintValidator<EmployeeUnique, Object> {

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;

    EmployeeUniqueValidator(UserRepository userRepository, EmployeeRepository employeeRepository) {
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public boolean isValid(Object employee, ConstraintValidatorContext context) {
        String employeeUsername = ((EmployeeDto) employee).getUsername();
        String employeeEmail = ((EmployeeDto) employee).getEmail();
        int employeeId = ((EmployeeDto) employee).getId();

        User userByUsername = userRepository.findByUsername(employeeUsername);
        User userByEmail = userRepository.findByEmail(employeeEmail);

        if (employeeUsername == null || employeeEmail == null) return false;

        if (employeeId == 0) {
            return userByUsername == null && userByEmail == null;
        } else {
            Optional<Employee> employeeById = employeeRepository.findById(employeeId);
            if (!employeeById.isPresent()) return false;
            if (userByUsername == null && userByEmail == null)
                return true;
            else if (userByUsername == null)
                return userByEmail.getId() == employeeById.get().getUser().getId();
            else if (userByEmail == null)
                return userByUsername.getId() == employeeById.get().getUser().getId();
            else
                return userByUsername.getId() == employeeById.get().getUser().getId() &&
                        userByEmail.getId() == employeeById.get().getUser().getId();
        }
    }
}
