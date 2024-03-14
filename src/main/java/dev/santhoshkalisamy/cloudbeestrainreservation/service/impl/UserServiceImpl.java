package dev.santhoshkalisamy.cloudbeestrainreservation.service.impl;

import dev.santhoshkalisamy.cloudbeestrainreservation.entity.UserDetails;
import dev.santhoshkalisamy.cloudbeestrainreservation.repository.UserRepository;
import dev.santhoshkalisamy.cloudbeestrainreservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails getUserByEmailId(String emailId) {
        return userRepository.findUserByEmail(emailId);
    }

    @Override
    public UserDetails addUser(UserDetails userDetails) {
        return userRepository.save(userDetails);
    }
}
