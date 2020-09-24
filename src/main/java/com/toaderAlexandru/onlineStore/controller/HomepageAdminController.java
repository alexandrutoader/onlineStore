package com.toaderAlexandru.onlineStore.controller;

import com.toaderAlexandru.onlineStore.domain.entity.User;
import com.toaderAlexandru.onlineStore.domain.model.ProductDto;
import com.toaderAlexandru.onlineStore.repository.UserRepository;
import com.toaderAlexandru.onlineStore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class HomepageAdminController {
    private final UserRepository userRepository;

    @Autowired
    private ProductService productService;

    public HomepageAdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/adminDashboard")
    public String signIn(HttpServletRequest request) {
        var password = (String)request.getParameter("password");
        var email = (String)request.getParameter("email");

        User user = userRepository.findByEmail(email);

        if (null == user) {
            throw new NullPointerException("Not found any user with email " + email);
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);

        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return "login";
        };

        return "indexAdmin";
    }

    @GetMapping("/products")
    public String getProducts(Model model) {
        List<ProductDto> productDtos = productService.getAll();

        model.addAttribute("products", productDtos);

        return "products";
    }
}
