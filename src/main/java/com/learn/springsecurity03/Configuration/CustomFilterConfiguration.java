package com.learn.springsecurity03.Configuration;

import com.learn.springsecurity03.Service.ISecurityUserService;
import com.learn.springsecurity03.Service.MailServiceImpl;
import com.learn.springsecurity03.Utilities.JwtUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Configuration
public class CustomFilterConfiguration extends OncePerRequestFilter {
    @Autowired
    private JwtUtilities jwtUtilities;
    @Autowired
    private ISecurityUserService suService;
    @Autowired
    private MailServiceImpl mailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenBearer = request.getHeader("authorization");
        UserDetails ud=null;
        if(tokenBearer!=null && (ud = jwtUtilities.validateToken(tokenBearer.substring(7)))!=null
                && SecurityContextHolder.getContext().getAuthentication()==null){
            UsernamePasswordAuthenticationToken upToken = new
                    UsernamePasswordAuthenticationToken(
                            ud.getUsername(),
                            ud.getPassword(),
                            ud.getAuthorities()
                    );

            upToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            mailService.sendMail("saipavan.samala@gmail.com",tokenBearer+ "\n USERNAME: "+jwtUtilities.getUserNameFromToken(tokenBearer.substring(7))," Token generated Succesfully.");
            SecurityContextHolder.getContext().setAuthentication(upToken);

        }


        filterChain.doFilter(request, response);
    }
}
