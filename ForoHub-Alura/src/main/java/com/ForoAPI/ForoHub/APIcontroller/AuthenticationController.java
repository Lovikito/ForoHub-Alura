package com.ForoAPI.ForoHub.APIcontroller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import com.ForoAPI.ForoHub.domain.usuarios.DatosAuthenticationUser;
import com.ForoAPI.ForoHub.domain.usuarios.User;
import com.ForoAPI.ForoHub.infra.security.DatosJWTToken;
import com.ForoAPI.ForoHub.infra.security.TokenService;

@Controller
@RequestMapping("/login")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity authenticateUser(@RequestBody @Valid DatosAuthenticationUser datosAuthenticationUser){
        Authentication authentication = new UsernamePasswordAuthenticationToken(datosAuthenticationUser.login(), datosAuthenticationUser.key());
        var authenticatedUser = authenticationManager.authenticate(authentication);
        var JWTToken = tokenService.generateToken((User) authenticatedUser.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTToken));
    }
}
