package com.controller;

import com.model.User;
import com.service.UserService;
import com.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Optional;

@Controller
@SessionAttributes("user")
public class InitController {

    private final UserService userService;

    @Autowired
    public InitController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping("/")
    public String init() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String index() {
        return "index";
    }

    @ModelAttribute("user")
    public User setUserToSession() {
        return new User();
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        @ModelAttribute("user") User user,
                        Model model) {
        String saltedPassword = "";
        User registeredUser = null;
        Optional<User> optionalUser = userService.getUserByEmail(email);
        if (optionalUser.isPresent()) {
            registeredUser = optionalUser.get();
            saltedPassword = HashUtil.getSaltedPassword(password, registeredUser.getSalt());
        }

        if (registeredUser != null && registeredUser.getPassword().equals(saltedPassword)) {
            user.setUserID(registeredUser.getUserID());
            user.setEmail(registeredUser.getEmail());
            user.setPassword(registeredUser.getPassword());
            user.setRole(registeredUser.getRole());
            user.setSalt(registeredUser.getSalt());
            if ("admin".equals(user.getRole().getRole())) {
                return "redirect:/admin/user";
            } else {
                return "redirect:/user/product";
            }
        } else {
            model.addAttribute("error", "Wrong password or email :(");
            return "index";
        }
    }
}
