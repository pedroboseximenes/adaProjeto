package com.ada.user.config;

import com.ada.user.core.FindByIdUserUseCase;
import com.ada.user.core.LoginUseCase;
import com.ada.user.core.RegisterUseCase;
import com.ada.user.core.UserPort;
import com.ada.user.adapter.UserJpaAdapter;
import com.ada.user.adapter.UserRepository;
import com.ada.user.validator.user.ValidatorUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Configuration
public class UserBeanConfiguration {
    private final UserRepository userRepository;

    public UserBeanConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Bean
    RegisterUseCase getRegisterUseCase(UserPort repo, List<ValidatorUser> validatorUserList) {
        return new RegisterUseCase(repo, passwordEncoder(), validatorUserList);
    }
    @Bean
    FindByIdUserUseCase getFindByIdUserUseCase(UserPort repo) {
        return new FindByIdUserUseCase(repo);
    }
    @Bean
    UserPort userRepositoryPort(UserRepository jpa) {
        return new UserJpaAdapter(jpa);
    }

    @Bean
    LoginUseCase getLoginUseCase(UserPort repo, AuthenticationManager authManager)   {
        return new LoginUseCase(repo,authManager);
    }
    @Bean
    UserDetailsService userDetailsService() {
        return  username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)  {
        return config.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService());

        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
}