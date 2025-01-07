package com.laundry.lavanderia.Model.Security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.Data;

// se crea la clase User que implementa UserDetails
@Data
public class User implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private String email;
    private boolean enabled;
    private List<Role> roles;

    // Se obtienen los roles del usuario
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // transforma los roles a una lista de GrantedAuthority
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();
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
        return enabled;
    }
}
