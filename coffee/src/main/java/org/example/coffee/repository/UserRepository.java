package org.example.coffee.repository;

import jakarta.persistence.EntityManager;
import org.example.coffee.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private final EntityManager em;

    public UserRepository(EntityManager em) {
        this.em = em;
    }

    public void save(User user) {
        em.persist(user);
    }
}
