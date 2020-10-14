package com.f97808.logisticscompany.jpa;

import com.f97808.logisticscompany.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);

    User findByEmail(String email);

    List<User> findByRole(String role);
}