package com.example.reading_tracker.repository;

import com.example.reading_tracker.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);
    List<User> findUsersByRolesId(Integer roleId);

    User findByEmail(String email);
    User findByNameAndSurname(String name, String surname);


}
