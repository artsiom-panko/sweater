package com.panko.sweater.controller;

import com.panko.sweater.entity.Message;
import com.panko.sweater.entity.User;
import com.panko.sweater.repository.MessageRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    private final MessageRepository messageRepository;

    public MainController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter,
                       Model model) {
        if (filter.isBlank()) {
            model.addAttribute("messages", messageRepository.findAll());
        } else {
            model.addAttribute("messages", messageRepository.findByTag(filter));
        }

        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String addMessage(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            Map<String, Object> model) {
        messageRepository.save(new Message(text, tag, user));
        model.put("messages", messageRepository.findAll());

        return "main";
    }
}
