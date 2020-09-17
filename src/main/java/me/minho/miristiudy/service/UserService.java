package me.minho.miristiudy.service;

import lombok.RequiredArgsConstructor;
import me.minho.miristiudy.domain.User;
import me.minho.miristiudy.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findByName(String name) {
        return userRepository.findByName(name);
    }
}
