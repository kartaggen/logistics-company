package com.f97808.logisticscompany.validation;

import com.f97808.logisticscompany.entity.Office;
import com.f97808.logisticscompany.jpa.OfficeRepository;
import com.f97808.logisticscompany.model.OfficeDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OfficeUniqueValidator implements ConstraintValidator<OfficeUnique, Object> {

    private final OfficeRepository repository;

    OfficeUniqueValidator(OfficeRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(Object office, ConstraintValidatorContext context) {
        String officeName = ((OfficeDto) office).getName();
        int officeId = ((OfficeDto) office).getId();
        Office officeUp = repository.findByName(officeName);
        if (officeUp == null) {
            return officeName != null;
        } else {
            return officeName != null && officeUp.getId() == officeId;
        }
    }
}
