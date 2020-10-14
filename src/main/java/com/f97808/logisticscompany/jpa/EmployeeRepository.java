package com.f97808.logisticscompany.jpa;

import com.f97808.logisticscompany.entity.Employee;
import com.f97808.logisticscompany.entity.Office;
import com.f97808.logisticscompany.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    List<Employee> findByOffice(Office office);
}