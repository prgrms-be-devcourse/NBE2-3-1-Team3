package com.example.coffeeboardproject.mapper;

import com.example.coffeeboardproject.dto.Cart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {

    List<Cart> orderdetail_selectAll();
    Cart orderdetail_select(Cart cart);
    int orderdetail_insert(Cart cart);
    int cart_delete(Cart cart);
    int orderdetail_finalinsert(Cart cart);
    List<Cart> selectfor_cartpersis();
    int updateorderidinto_cartpersis(int orderid);
    int insertAllinto_cartpersis(Cart cart);
}
