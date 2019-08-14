package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/payment")
public class OrderController {
//
//    private MailService mailService;
//    private OrderService orderService;
//    private CodeService codeService;
//    private BasketService basketService;
//
//    @Autowired
//    public OrderController(MailService mailService, OrderService orderService,
//                           CodeService codeService, BasketService basketService) {
//        this.mailService = mailService;
//        this.orderService = orderService;
//        this.codeService = codeService;
//        this.basketService = basketService;
//    }
//
//    @GetMapping
//    public ModelAndView showPaymentPage() {
//        return new ModelAndView("payment", "order", new Order());
//    }
//
//    @PostMapping
//    public String createOrder(@ModelAttribute("order") Order order,
//                              @SessionAttribute("user") User user) {
//        codeService.add(new Code(user));
//        Code code = null;
//        Optional<Code> optionalCode = codeService.getLastCodeForUser(user);
//        if (optionalCode.isPresent()) {
//            code = optionalCode.get();
//        }
//
//        Basket basket = null;
//        Optional<Basket> optionalBasket = basketService.getBasketByUser(user);
//        if (optionalBasket.isPresent()) {
//            basket = optionalBasket.get();
//        }
//
//        order.setUser(user);
//        order.setBasket(basket);
//        order.setCode(code);
//        orderService.add(order);
//        new Thread(() -> mailService.sendConfirmCode(order)).start();
//        return "redirect:/user/payment/confirm";
//    }
//
//    @GetMapping("/confirm")
//    public String showConfirmOrderPage() {
//        return "payment_confirm";
//    }
//
//    @PostMapping("/confirm")
//    public String confirmOrder(@RequestParam("confirm") String confirm,
//                               @SessionAttribute("user") User user,
//                               Model model) {
//        Optional<Order> optionalOrder = orderService.getLastOrderForUser(user);
//        if (optionalOrder.isPresent()) {
//            Order order = optionalOrder.get();
//            if (order.getCode().getValue().equals(confirm)) {
//                Basket basket = new Basket(order.getUser());
//                basketService.add(basket);
//                model.addAttribute("message", "Покупка успешно совершена!");
//            } else {
//                model.addAttribute("message", "Неверный код. Введите заново!");
//            }
//        }
//        return "payment_confirm";
//    }
}
