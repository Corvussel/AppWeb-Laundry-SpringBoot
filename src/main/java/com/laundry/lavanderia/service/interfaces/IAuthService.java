package com.laundry.lavanderia.service.interfaces; 
import org.springframework.security.core.Authentication;

public interface IAuthService {

    Authentication authenticate(String username, String password);
    void logout();
}
