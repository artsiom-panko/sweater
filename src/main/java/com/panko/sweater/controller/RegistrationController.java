package com.panko.sweater.controller;

import com.panko.sweater.entity.Role;
import com.panko.sweater.entity.User;
import com.panko.sweater.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final UserRepository userRepository;

    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String registration() {
        return "registration";
    }

    // TODO DTO
    @PostMapping
    public String addNewUser(User user, Map<String, Object> model) {
        if (Objects.nonNull(userRepository.findByUsername(user.getUsername()))) {
            model.put("message", "User already exists");
            return "registration";
        } else {
            model.put("message", "Ready!");
            user.setRoles(Collections.singleton(Role.USER));
            user.setActive(true);
            userRepository.save(user);
        }

        return "redirect:/login";
    }
}
