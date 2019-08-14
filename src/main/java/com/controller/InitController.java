package com.controller;

import com.model.Cart;
import com.model.Code;
import com.model.Order;
import com.model.Product;
import com.model.Role;
import com.model.User;
import com.service.CartService;
import com.service.CodeService;
import com.service.OrderService;
import com.service.ProductService;
import com.service.RoleService;
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

import java.util.ArrayList;
import java.util.Optional;

@Controller
@SessionAttributes("user")
public class InitController {

    private RoleService roleService;
    private UserService userService;
    private ProductService productService;
    private CartService cartService;
    private OrderService orderService;
    private CodeService codeService;

    @Autowired
    public InitController(RoleService roleService, UserService userService,
                          CartService cartService, ProductService productService,
                          OrderService orderService, CodeService codeService) {
        this.roleService = roleService;
        this.userService = userService;
        this.cartService = cartService;
        this.productService = productService;
        this.orderService = orderService;
        this.codeService = codeService;
    }

    @ModelAttribute("user")
    public User setUserToSession(User user) {
        return user;
    }

    @GetMapping("/")
    public String init() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String index() {
        return "index";
    }

    @PostMapping("/login")
    public String login(@RequestParam("login") String login,
                        @RequestParam("password") String password,
                        @ModelAttribute("user") User user,
                        Model model) {
        String saltedPassword = "";
        User registeredUser = null;
        Optional<User> optionalUser = userService.getUserByEmail(login);
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
            if ("admin".equals(user.getRole())) {
                return "redirect:/admin/user";
            } else {
                return "redirect:/user/product";
            }
        } else {
            model.addAttribute("error", "Пользователь с таким логином и паролем не найден");
            return "index";
        }
    }

    @GetMapping("/init")
    public String addUser() {
        Role admin = new Role(1L, "admin");
        roleService.addRole(admin);

        User user = new User(1L, "user@u.u", "1", admin);
        userService.addUser(user);

        Product cup = new Product(1L, "cup", "description", 0.5);
        productService.addProduct(cup);

        Cart cart = new Cart(1L, new ArrayList<>(), user);
        cartService.addCart(cart);

        Order order = new Order(1L, user, "user@u.u", "skd", new ArrayList<>());
        orderService.addOrder(order);

        Code code = new Code(1L, "1221", order, "user@u.u");
        codeService.addCode(code);
        return "index";
    }
}
