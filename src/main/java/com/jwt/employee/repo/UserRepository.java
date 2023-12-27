package com.jwt.employee.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.employee.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

   public Optional<User> findByEmail(String email);
}
