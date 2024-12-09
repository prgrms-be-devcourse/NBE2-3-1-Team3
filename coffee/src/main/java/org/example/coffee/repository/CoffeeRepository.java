package org.example.coffee.repository;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.example.coffee.domain.Coffee;

import java.util.List;

@Repository
public class CoffeeRepository {

    @Autowired // EntityManager 주입
    private EntityManager em;

    // 모든 커피 조회
    public List<Coffee> findAll() {
        String sql = "select c from Coffee c";
        return em.createQuery(sql, Coffee.class).getResultList();
    }

    public Coffee findById(int coffeeId) {
        Coffee coffee = em.find(Coffee.class, coffeeId);
        if (coffee == null) {
            throw new IllegalArgumentException("해당하는 커피 id가 없습니다.");
        } else {
            return coffee;
        }
    }
}
