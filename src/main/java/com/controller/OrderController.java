package com.controller;

import com.model.Cart;
import com.model.Order;
import com.model.User;
import com.service.CartService;
import com.service.CodeService;
import com.service.MailService;
import com.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/user/payment")
public class OrderController {

    private MailService mailService;
    private OrderService orderService;
    private CodeService codeService;
    private CartService cartService;

    @Autowired
    public OrderController(MailService mailService, OrderService orderService,
                           CodeService codeService, CartService cartService) {
        this.mailService = mailService;
        this.orderService = orderService;
        this.codeService = codeService;
        this.cartService = cartService;
    }

    @GetMapping
    public ModelAndView showPaymentPage() {
        return new ModelAndView("payment", "order", new Order());
    }

    @PostMapping
    public String createOrder(@ModelAttribute("order") Order order,
                              @RequestParam("email") String email,
                              @RequestParam("address") String address,
                              @AuthenticationPrincipal User user) {
        Cart cart = null;
        Optional<Cart> optionalCart = cartService.getCart(user);
        if (optionalCart.isPresent()) {
            cart = optionalCart.get();
        }
        orderService.createOrder(user, email, address, cart.getProducts());
        orderService.sendConfirmationCode(email, order);
        return "redirect:/user/payment/confirm";
    }

    @GetMapping("/confirm")
    public String showConfirmOrderPage() {
        return "payment_confirm";
    }

    @PostMapping("/confirm")
    public String confirmOrder(@RequestParam("confirm") String confirm,
                               @AuthenticationPrincipal User user,
                               Model model) {
        Optional<Order> optionalOrder = orderService.getUserOrder(user);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            if (order.getCode().getCode().equals(confirm)) {
                Cart cart = new Cart(new ArrayList<>(), user);
                cartService.addCart(cart);
                model.addAttribute("message", "Success");
            } else {
                model.addAttribute("message", "Fail");
            }
        }
        return "payment_confirm";
    }
}
