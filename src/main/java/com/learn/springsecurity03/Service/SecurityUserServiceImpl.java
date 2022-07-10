package com.learn.springsecurity03.Service;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.learn.springsecurity03.Model.Security.ISecurityUserRepository;
import com.learn.springsecurity03.Model.Security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserServiceImpl implements ISecurityUserService {
    @Autowired
    private ISecurityUserRepository suRepo;

    @Autowired
    private MailServiceImpl mailService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return suRepo.findSecurityUserByUsername(username);
    }
    public SecurityUserServiceImpl(ISecurityUserRepository suRepo) {
        this.suRepo =suRepo;
        this.suRepo.save(SecurityUser.builder().username("admin").password("password").build());
        this.suRepo.flush();

    }
}
