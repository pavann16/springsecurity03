package com.learn.springsecurity03.Model.Security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "SECURITY_USER")
@Setter @ToString
@AllArgsConstructor @NoArgsConstructor @Builder
public class SecurityUser implements UserDetails {

    @Id
    @GeneratedValue(generator = "su_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "su_generator", sequenceName = "SECURITY_USER_SEQUENCE")
    private Long id;

    @JsonProperty("username")
    @Column(name = "USER_NAME")
    private String username;

    @JsonProperty("password")
    @Column(name = "PASSWORD")
    private String password;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
