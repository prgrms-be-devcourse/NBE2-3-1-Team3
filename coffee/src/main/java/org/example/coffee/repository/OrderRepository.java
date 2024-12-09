package org.example.coffee.repository;

import jakarta.persistence.EntityManager;
import org.example.coffee.domain.Order;
import org.example.coffee.domain.OrderDetail;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class OrderRepository {

    private final EntityManager em;

    public OrderRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Order order) {
        em.persist(order);
    }

    public void saveOrderDetail(OrderDetail orderDetail) {
        em.persist(orderDetail);
    }

    public List<Order> findAll() {
        return em.createQuery("select o from Order o", Order.class).getResultList();
    }

    ////////////////
    // 어제 오후 2시 이전 (배송 완료)
    public List<Order> findOrdersBefore(LocalDateTime cutoffTime) {
        return em.createQuery(
                        "SELECT o FROM Order o WHERE o.orderDate < :cutoffTime", Order.class)
                .setParameter("cutoffTime", cutoffTime)
                .getResultList();
    }

    // 어제 오후 2시 이후 ~ 오늘 오후 2시 미만 (배송 예정)
    public List<Order> findOrdersBetween(LocalDateTime startTime, LocalDateTime endTime) {
        return em.createQuery(
                        "SELECT o FROM Order o WHERE o.orderDate >= :startTime AND o.orderDate < :endTime", Order.class)
                .setParameter("startTime", startTime)
                .setParameter("endTime", endTime)
                .getResultList();
    }

    // 오늘 오후 2시 이후 (결제 완료)
    public List<Order> findOrdersAfter(LocalDateTime cutoffTime) {
        return em.createQuery(
                        "SELECT o FROM Order o WHERE o.orderDate >= :cutoffTime", Order.class)
                .setParameter("cutoffTime", cutoffTime)
                .getResultList();
    }


    //////////////////////////
    public List<Order> find(String email) {
        return em.createQuery(
                        "SELECT o FROM Order o WHERE o.email = :email", Order.class)
                .setParameter("email", email)
                .getResultList();
    }




}
