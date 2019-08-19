package com.controller;

import com.model.Cart;
import com.model.Product;
import com.model.User;
import com.service.CartService;
import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/user/product")
public class CartController {

    private CartService cartService;
    private ProductService productService;

    @Autowired
    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping
    public String showAllUserProducts(@AuthenticationPrincipal User user, Model model) {
        Optional<Cart> optionalBasket = cartService.getCart(user);
        optionalBasket.ifPresent(basket ->
                model.addAttribute("size", cartService.getCart(user).get().getProducts().size()));
        model.addAttribute("productList", productService.getAll());
        return "products_user";
    }

    @GetMapping("/buy/{id}")
    public String showCartSize(@PathVariable("id") Long id,
                               @AuthenticationPrincipal User user) {
        Product product = null;
        Optional<Product> optionalProduct = productService.getProductById(id);
        if (optionalProduct.isPresent()) {
            product = optionalProduct.get();
        }

        Cart cart = null;
        Optional<Cart> optionalCart = cartService.getCart(user);
        if (optionalCart.isPresent()) {
            cart = optionalCart.get();
        }

        cartService.addProductToCart(cart, product);
        return "redirect:/user/product";
    }
}
