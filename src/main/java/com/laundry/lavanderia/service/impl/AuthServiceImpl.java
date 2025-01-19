package com.laundry.lavanderia.service.impl;
 
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.laundry.lavanderia.service.interfaces.IAuthService;

@Service
public class AuthServiceImpl implements IAuthService {

    private final AuthenticationManager authenticationManager;
 
    public AuthServiceImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * Autentica un usuario con su nombre de usuario y contrase a.
     * 
     * Si el usuario y la contrase a son correctos, se devuelve un objeto
     * Authentication con la informaci n del usuario autenticado. Si el
     * usuario o la contrase a son incorrectos, se lanza una excepci n
     * AuthenticationException.
     * 
     * @param username el nombre de usuario del usuario a autenticar.
     * @param password la contrase a del usuario a autenticar.
     * @return el objeto Authentication con la informaci n del usuario
     *         autenticado.
     * @throws AuthenticationException si el usuario o la contrase a son
     *                                 incorrectos.
     */
    @Override
    public Authentication authenticate(String username, String password) throws AuthenticationException {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    // metodo para
    @Override
    public void logout() {

    }

}
