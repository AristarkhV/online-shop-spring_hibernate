package com.controller;

import com.model.Cart;
import com.model.User;
import com.service.CartService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/admin/user")
public class UserController {

    private UserService userService;
    private CartService cartService;

    @Autowired
    public UserController(UserService userService, CartService cartService) {
        this.userService = userService;
        this.cartService = cartService;
    }

    @GetMapping
    public String showAllUsers(Model model) {
        model.addAttribute("usersList", userService.getAll());
        return "users";
    }

    @GetMapping("/add")
    public ModelAndView showAddUserPage() {
        return new ModelAndView("addUser", "user", new User());
    }

    @PostMapping("/add")
    public ModelAndView addUser(@ModelAttribute("user") User user,
                                @RequestParam("confirmPassword") String confirmPassword,
                                ModelMap model) {
        String email = user.getEmail();
        String login = user.getEmail();
        String password = user.getPassword();
        String role = user.getRole().getRole();
        Optional<User> optionalPresentUser = userService.getUserByEmail(email);
        if (email.isEmpty() || login.isEmpty() || password.isEmpty() || role == null) {
            model.addAttribute("error", "Empty fields!");
        } else if (optionalPresentUser.isPresent()) {
            model.addAttribute("error", "Пользователь с таким логином " +
                    "или электронной почтой уже зарегистрирован!");
        } else if (!password.equals(confirmPassword) || password.length() > 16) {
            model.addAttribute("error", "Passwords not equals or too long!");
        } else {
            userService.addUser(user);
            Optional<User> optionalNewUser = userService.getUserByEmail(email);
            if (optionalNewUser.isPresent()) {
                Cart cart = new Cart(new ArrayList<>(), optionalNewUser.get());
                cartService.addCart(cart);
            }
            return new ModelAndView("redirect:/admin/user");
        }
        return new ModelAndView("addUser", model);
    }

    @GetMapping("/change/{id}")
    public String showEditUserPage(@PathVariable("id") Long id, Model model) {
        Optional<User> optionalUser = userService.getUserById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            model.addAttribute("user", user);
        }
        return "change_user";
    }

    @PostMapping("/change")
    public String applyEditUser(@ModelAttribute("user") User user,
                                @RequestParam("confirmPassword") String confirmPassword,
                                ModelMap model) {
        String login = user.getEmail();
        String email = user.getEmail();
        String password = user.getPassword();
        String role = user.getRole().getRole();
        if (email.isEmpty() || login.isEmpty() || password.isEmpty() || role.isEmpty()) {
            model.addAttribute("error", "Empty fields!");
            model.addAttribute("user", user);
        } else if (!(password.equals(confirmPassword))) {
            model.addAttribute("error", "Passwords not equals!");
            model.addAttribute("user", user);
        } else {
            userService.editUser(user);
            return "redirect:/admin/user";
        }
        return "change_user";
    }

    @GetMapping("/delete/{id}")
    public String removeUser(@PathVariable("id") Long id) {
        userService.deleteUser(userService.getUserById(id).get());
        return "redirect:/admin/user";
    }
}
