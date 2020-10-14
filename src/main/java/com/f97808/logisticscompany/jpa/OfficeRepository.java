package com.f97808.logisticscompany.jpa;

import com.f97808.logisticscompany.entity.Office;
import org.springframework.data.repository.CrudRepository;

public interface OfficeRepository extends CrudRepository<Office, Integer> {
    Office findByName(String name);
}