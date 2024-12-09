package org.example.coffee.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/home")
    public String showCoffeePage(Model model) {
        return "coffeePage";
    }

    @GetMapping("/shipping")
    public String showShipPage(Model model) {
        return "shippingPage";
    }
    @GetMapping("/orderHistory")
    public String showOrderHistoryPage(Model model) {
        return "orderHistoryPage";
    }
}
