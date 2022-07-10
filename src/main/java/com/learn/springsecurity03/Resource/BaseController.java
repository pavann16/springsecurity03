package com.learn.springsecurity03.Resource;

import com.learn.springsecurity03.Model.Security.SecurityUser;
import com.learn.springsecurity03.Service.ISecurityUserService;
import com.learn.springsecurity03.Service.MailServiceImpl;
import com.learn.springsecurity03.Utilities.JwtUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("")
public class BaseController {
    @Autowired
    private ISecurityUserService suService;
    @Autowired
    private JwtUtilities jwtUtilities;
    @Autowired
    private MailServiceImpl mailService;

    @GetMapping
    public String welcome(){
        return "Welcome to Application.";
    }
    @PostMapping("/token")
    public String token(@RequestBody SecurityUser su) throws ExecutionException, InterruptedException {

        if(su!=null){
            UserDetails su1  = suService.loadUserByUsername(su.getUsername());

            if(su1!=null){
                String token = jwtUtilities.generateToken(su1);
                CompletableFuture<String> asyncOperation  = CompletableFuture.supplyAsync(()-> mailService.sendMail("saipavan.samala@gmail.com",token+ "\n USERNAME: "+jwtUtilities.getUserNameFromToken(token)," Token generated Succesfully."));
               // mailService.sendMail("saipavan.samala@gmail.com",token+ "\n USERNAME: "+jwtUtilities.getUserNameFromToken(token)," Token generated Succesfully.");
                if(!asyncOperation.isDone()){
                    System.out.println("Operation yet to Complete....");
                    return token+" incomplete";
                }
                //String result = asyncOperation.get();
                return token;
            }
        }
        return "Bad Credentials";
    }
}
