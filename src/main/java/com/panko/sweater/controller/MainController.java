package com.panko.sweater.controller;

import com.panko.sweater.entity.Message;
import com.panko.sweater.entity.User;
import com.panko.sweater.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        model.put("messages", messageRepository.findAll());

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

    @PostMapping("/filter")
    public String filterMessages(@RequestParam String tag,
                                 Map<String, Object> model) {
        if (tag.isBlank()) {
            model.put("messages", messageRepository.findAll());
        } else {
            model.put("messages", messageRepository.findByTag(tag));
        }

        return "main";
    }
}
