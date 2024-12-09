package com.example.coffeeboardproject.controller;

import com.example.coffeeboardproject.dto.Cart;
import com.example.coffeeboardproject.dto.Coffee;
import com.example.coffeeboardproject.dto.Order;
import com.example.coffeeboardproject.dto.User;
import com.example.coffeeboardproject.service.CartService;
import com.example.coffeeboardproject.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;

    @RequestMapping("/list.do")
    public String list(HttpServletRequest request, Model model) {
        List<Coffee> coffeeList = cartService.coffeeSelect();
        model.addAttribute("coffeeLists", coffeeList);

        Cart cart = new Cart();
        Coffee coffee = new Coffee();


        if (request.getParameter("coffee_id") != null) {
            cart.setCoffee_id(Integer.parseInt(request.getParameter("coffee_id")));
            cart.setOrder_id( 0 );
            cart.setQuantity( 1 );

            int cartFlag = cartService.cartInsert(cart);
            model.addAttribute("cartFlag", cartFlag);

            // Coffee 객체에 위에 이미 커피 객체에 가져온 값들을 세팅한다. 이미 세팅되어 있눈 것 같으니 나중에 get으로만 다시 돌려보기
            coffee.setCoffee_id(Integer.parseInt(request.getParameter("coffee_id")));
            coffee.setCoffee_name(coffee.getCoffee_name());
            System.out.println("coffeeName : " + coffee.getCoffee_name());
            coffee.setCoffee_price(coffee.getCoffee_price());

            // cart 객체에 파라미터로 받아온 커피 아이디 세팅하고 나머지 필드 세팅하기 - 필드 하나를 특정 값을 가져오면 다른 값들은 그에 맞게 가져오나?
            cart.setQuantity(cart.getQuantity());
            coffee.setCoffee_id(cart.getCoffee_id()); // 제대로 들어감
            cart.setOrder_id(cart.getOrder_id());

            model.addAttribute("cartList", cartService.cartSelect(cart));

        }

        List<Cart> cartSelectAll = cartService.cartSelectAll();
        System.out.println("cartLists : " + cartSelectAll);
        model.addAttribute("cartLists", cartSelectAll);


        return "board_list1";
    }

}
