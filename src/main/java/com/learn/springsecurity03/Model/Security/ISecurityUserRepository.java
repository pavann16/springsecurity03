package com.learn.springsecurity03.Model.Security;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ISecurityUserRepository extends JpaRepository<SecurityUser, Long> {
        SecurityUser  findSecurityUserByUsername(String username);
}
