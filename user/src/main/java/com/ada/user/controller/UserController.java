package com.ada.user.controller;

import com.ada.user.core.FindByIdUserUseCase;
import com.ada.user.core.LoginUseCase;
import com.ada.user.core.RegisterUseCase;
import com.ada.user.domain.LoginResponse;
import com.ada.user.domain.User;
import com.ada.user.adapter.JpaUserEntity;
import com.ada.user.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class UserController {
    private final RegisterUseCase registerUseCase;
    private final LoginUseCase loginUseCase;
    private final FindByIdUserUseCase findByIdUserUseCase;
    private final JwtService jwtService;

    public UserController(RegisterUseCase registerUseCase, LoginUseCase loginUseCase, JwtService jwtService, FindByIdUserUseCase findByIdUserUseCase) {
        this.jwtService = jwtService;
        this.registerUseCase = registerUseCase;
        this.loginUseCase = loginUseCase;
        this.findByIdUserUseCase = findByIdUserUseCase;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody User registerUserDto) {
        User registeredUser = registerUseCase.execute(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        User registeredUser = findByIdUserUseCase.execute(id);

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
