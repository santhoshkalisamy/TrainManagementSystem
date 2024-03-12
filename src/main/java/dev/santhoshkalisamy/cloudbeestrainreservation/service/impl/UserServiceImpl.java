package dev.santhoshkalisamy.cloudbeestrainreservation.service.impl;

import dev.santhoshkalisamy.cloudbeestrainreservation.entity.UserDetails;
import dev.santhoshkalisamy.cloudbeestrainreservation.repository.UserRepository;
import dev.santhoshkalisamy.cloudbeestrainreservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails getUserByEmailId(String emailId) {
        return userRepository.findUserByEmail(emailId);
    }

    @Override
    public UserDetails getUserById(int id) {
        Optional<UserDetails> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public UserDetails addUser(UserDetails userDetails) {
        return userRepository.save(userDetails);
    }
}
