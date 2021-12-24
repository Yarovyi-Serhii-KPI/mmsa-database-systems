package edu.kpi.iasa.workshop.controller;


import edu.kpi.iasa.workshop.dto.JWTRequestDto;
import edu.kpi.iasa.workshop.dto.UserDto;
import edu.kpi.iasa.workshop.dto.JWTResponseDto;
import edu.kpi.iasa.workshop.dto.RegistrationDto;
import edu.kpi.iasa.workshop.service.RoleService;
import edu.kpi.iasa.workshop.model.User;
import edu.kpi.iasa.workshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

@Controller
public class UserController {

    private static final String DEFAULT_ROLE = "USER";
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private UserDto createUserDto(User user) {
        return new UserDto(user.getUsername());
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody RegistrationDto registrationDto) {
        User user = createUser(registrationDto);
        return ResponseEntity.ok(createUserDto(userService.createUser(user)));
    }

    private User createUser(RegistrationDto registrationDto) {
        User user = User.builder()
                .username(registrationDto.getUsername())
                .password(bCryptPasswordEncoder().encode(registrationDto.getPassword()))
                .email(registrationDto.getEmail())
                .uploads(registrationDto.getUploads())
                .downloads(registrationDto.getDownloads())
                .score(registrationDto.getUploads()-registrationDto.getDownloads())
                .registration_date(LocalDate.now())
                .build();
        user.setRoles(Collections.singleton(roleService.getRoleByCode(DEFAULT_ROLE)));
        return user;
    }
}
