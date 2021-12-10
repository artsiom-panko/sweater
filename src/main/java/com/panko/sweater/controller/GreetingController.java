package com.panko.sweater.controller;

import com.panko.sweater.entity.Message;
import com.panko.sweater.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Controller
public class GreetingController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", defaultValue = "World", required = false)
                                   String name, Map<String, Object> model) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping
    public String main(Map<String, Object> model) {
        model.put("messages", messageRepository.findAll());

        return "main";
    }

    @PostMapping
    public String addMessage(@RequestParam String text,
                             @RequestParam String tag,
                             Map<String, Object> model) {
        messageRepository.save(new Message(text, tag));
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
