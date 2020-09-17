package me.minho.miristiudy.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import me.minho.miristiudy.domain.User;
import me.minho.miristiudy.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Getter
    @NoArgsConstructor
    public static class LoginRequest {
        private String username;
        private String password;
    }

    @PostMapping(value = "/api/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> login(@RequestBody LoginRequest loginRequest, HttpSession httpSession) {
        User findUser = userService.findByName(loginRequest.username);
        if (findUser == null || !findUser.matchPassword(loginRequest.password)) {
            return ResponseEntity.notFound().build();
        }

        httpSession.setAttribute("userId", findUser.getId());
        return ResponseEntity.ok(findUser.getId());
    }
}
