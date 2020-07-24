package com.siit.spring.controller;

import com.siit.spring.domain.entity.User;
import com.siit.spring.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user")
    public String user() {
        return "login";
    }

    @PostMapping("/signIn")
    public String signIn(HttpServletRequest request) {
        var password = (String)request.getParameter("password");
        var email = (String)request.getParameter("email");

        User user = userRepository.findByEmail(email);

        if (null == user) {
            throw new NullPointerException("Not found any user with email " + email);
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        if (!bCryptPasswordEncoder.matches(user.getPassword(), encodedPassword)) {
            throw new IllegalArgumentException("User or email incorrect! Please try again!");
        };

        return "index";
    }
}
