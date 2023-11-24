package com.example.reading_tracker.repository;

import com.example.reading_tracker.bean.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);
    List<Role> findRolesByUsersId(Integer userId);

}

