package com.controller;

import com.model.Product;
import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/admin/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showAllProducts(Model model) {
        model.addAttribute("productList", productService.getAll());
        return "products_admin";
    }

    @GetMapping("/add")
    public ModelAndView showAddProductPage() {
        return new ModelAndView("addProduct", "product", new Product());
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        if (product.getPrice() == null) {
            product.setPrice(0.0);
        }
        productService.addProduct(product);
        return "redirect:/admin/product";
    }

    @GetMapping("/change/{id}")
    public String showChangeProductPage(@PathVariable Long id, Model model) {
        Optional<Product> optionalProduct = productService.getProductById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            model.addAttribute("product", product);
        }
        return "change_product";
    }

    @PostMapping("/change")
    public String changeProduct(@ModelAttribute Product product) {
        if (product.getPrice() == null) {
            product.setPrice(0.0);
        }
        productService.editProduct(product);
        return "redirect:/admin/product";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(productService.getProductById(id).get());
        return "redirect:/admin/product";
    }
}
