package com.example.coffeeboardproject.controller;

import com.example.coffeeboardproject.dto.Cart;
import com.example.coffeeboardproject.dto.Coffee;
import com.example.coffeeboardproject.dto.Order;
import com.example.coffeeboardproject.dto.User;
import com.example.coffeeboardproject.service.CartService;
import com.example.coffeeboardproject.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;

    // 결제하기를 누르면 실행
    @RequestMapping("/manager.do")
    public String manager(HttpServletRequest request, Model model) {
        User user = new User();
        user.setEmail(request.getParameter("email"));
        user.setAddress(request.getParameter("address"));
        user.setZipcode(request.getParameter("postcode"));
        model.addAttribute("userFlag", orderService.userInsert(user));

        Order order = new Order();
        order.setEmail(request.getParameter("email"));
        //System.out.println(request.getParameter("email"));
        order.setTotal_price(Integer.parseInt(request.getParameter("totalPrice")));
        System.out.println(Integer.parseInt(request.getParameter("totalPrice")));
        order.setOrder_date(request.getParameter("orderDate"));

        if ( Integer.parseInt(request.getParameter("totalPrice")) != 0 ) {
            model.addAttribute("orderFlag", orderService.orderInsert(order));
        } else if ( Integer.parseInt(request.getParameter("totalPrice")) == 0 ) {
            model.addAttribute("orderFlag", 1);
        }

        Cart cart = new Cart();

        List<Cart> selectforCartpersisList = cartService.selectforCartpersis();
        for (Cart car : selectforCartpersisList) {
            System.out.println(car);
            cartService.insertAllintoCartpersis(car);
        }

        cartService.updateOrderIdintoCartpersis(order.getOrder_id());
        model.addAttribute("deleteFlag", cartService.cartDelete(cart));

        return "board_manager1";
    }
}
