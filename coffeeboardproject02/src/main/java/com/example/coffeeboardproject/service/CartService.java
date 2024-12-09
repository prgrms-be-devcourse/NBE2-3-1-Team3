package com.example.coffeeboardproject.service;

import com.example.coffeeboardproject.dto.Cart;
import com.example.coffeeboardproject.dto.Coffee;
import com.example.coffeeboardproject.mapper.CartMapper;
import com.example.coffeeboardproject.mapper.CoffeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CoffeeMapper coffeeMapper;

    @Autowired
    private CartMapper cartMapper;

    public List<Coffee> coffeeSelect() {
        System.out.println("coffeeSelect 호출");
        List<Coffee> lists = coffeeMapper.coffee_select();

        //System.out.println("coffeeSelect " + lists.size());

        return lists;
    }


    public List<Cart> cartSelectAll() {
        System.out.println("coffeeSelect 호출");
        List<Cart> cartlists = cartMapper.orderdetail_selectAll();

        System.out.println("coffeeSelect " + cartlists.size());

        return cartlists;
    }

    public Cart cartSelect(Cart cart) {
        System.out.println("cartSelect 호출");

        cart = cartMapper.orderdetail_select(cart);
        //System.out.println("cart: " + cart.toString());

        return cart;
    }

    public int cartInsert(Cart cart) {
        System.out.println("cartInsert 호출");
        int flag = 2;
        //System.out.println("cartInsert_quantity: " + cart.getQuantity());
        int result = cartMapper.orderdetail_insert(cart);

        if (result == 1) {
            flag = 0;
        } else {
            flag = 1;
        }

        return flag;
    }


    public List<Cart> selectforCartpersis() {
        System.out.println("selectforCartpersis 호출");
        List<Cart> lists = cartMapper.selectfor_cartpersis();

        System.out.println("selectforCartpersisListssize: " + lists.size());

        return lists;
    }

    public int updateOrderIdintoCartpersis(int orderid) {
        System.out.println("insertintoCartpersis 호출");

        int flag =2;
        int result = cartMapper.updateorderidinto_cartpersis(orderid);

        if (result == 1) {
            flag = 0;
        } else {
            flag = 1;
        }

        return flag;
    }

    public int insertAllintoCartpersis(Cart cart) {
        System.out.println("insertAllintoCartpersis 호출");
        int flag =2;
        int result = cartMapper.insertAllinto_cartpersis(cart);

        if (result == 1) {
            flag = 0;
        } else {
            flag = 1;
        }

        return flag;
    }

    public int cartDelete(Cart cart) {
        System.out.println("cartDelete 호출");
        int flag = 2;
        int result = cartMapper.cart_delete(cart);

        if (result == 0) {
            flag = 1;
        } else {
            flag = 0;
        }

        return flag;
    }

    public int cartFinalInsert(Cart cart) {
        System.out.println("cartDelete 호출");
        int flag = 2;

        int result = cartMapper.orderdetail_finalinsert(cart);

        if (result == 0) {
            flag = 1;
        } else {
            flag = 0;
        }

        return flag;
    }


}
