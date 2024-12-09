package org.example.coffee.service;

import jakarta.transaction.Transactional;
import org.example.coffee.domain.User;
import org.example.coffee.dto.UserRequest;
import org.example.coffee.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveUser(UserRequest userRequest) {
        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setAddress(userRequest.getAddress());
        user.setZipcode(userRequest.getZipcode());
        userRepository.save(user);
    }
}
