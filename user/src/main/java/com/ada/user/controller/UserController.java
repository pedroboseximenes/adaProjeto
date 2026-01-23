package com.ada.user.controller;

import com.ada.user.core.LoginUseCase;
import com.ada.user.core.RegisterUseCase;
import com.ada.user.domain.LoginResponse;
import com.ada.user.domain.User;
import com.ada.user.adapter.JpaUserEntity;
import com.ada.user.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class UserController {
    private final RegisterUseCase registerUseCase;
    private final LoginUseCase loginUseCase;
    private final JwtService jwtService;

    public UserController(RegisterUseCase registerUseCase, LoginUseCase loginUseCase, JwtService jwtService) {
        this.jwtService = jwtService;
        this.registerUseCase = registerUseCase;
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody User registerUserDto) {
        User registeredUser = registerUseCase.execute(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody User loginUserDto) {
        JpaUserEntity authenticatedUser = loginUseCase.execute(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
