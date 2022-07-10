package com.learn.springsecurity03.Resource;

import com.learn.springsecurity03.Model.Security.SecurityUser;
import com.learn.springsecurity03.Service.ISecurityUserService;
import com.learn.springsecurity03.Utilities.JwtUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class BaseController {
    @Autowired
    private ISecurityUserService suService;
    @Autowired
    private JwtUtilities jwtUtilities;


    @GetMapping
    public String welcome(){
        return "Welcome to Application.";
    }
    @PostMapping("/token")
    public String token(@RequestBody SecurityUser su){

        if(su!=null){
            UserDetails su1  = suService.loadUserByUsername(su.getUsername());

            if(su1!=null){
                return jwtUtilities.generateToken(su1);
            }
        }
        return "Bad Credentials";
    }
}
