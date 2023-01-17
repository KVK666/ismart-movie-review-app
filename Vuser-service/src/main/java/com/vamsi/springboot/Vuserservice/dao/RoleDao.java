package com.vamsi.springboot.Vuserservice.dao;

import com.vamsi.springboot.Vuserservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, String> {
}
