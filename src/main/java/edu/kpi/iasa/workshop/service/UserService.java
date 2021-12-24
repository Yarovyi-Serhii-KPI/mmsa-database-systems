package edu.kpi.iasa.workshop.service;

import edu.kpi.iasa.workshop.dto.JWTRequestDto;
import edu.kpi.iasa.workshop.dto.JWTResponseDto;
import edu.kpi.iasa.workshop.dto.RegistrationDto;
import edu.kpi.iasa.workshop.dto.UserDto;
import edu.kpi.iasa.workshop.exception.SongNotFoundException;
import edu.kpi.iasa.workshop.exception.UserNotFoundException;
import edu.kpi.iasa.workshop.model.Song;
import edu.kpi.iasa.workshop.model.User;
import edu.kpi.iasa.workshop.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }


    public User getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            log.info("user: {}", user.get());
            return user.get();
        }
        throw new UserNotFoundException();
    }



}
