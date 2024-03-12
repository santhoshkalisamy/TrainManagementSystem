package dev.santhoshkalisamy.cloudbeestrainreservation.service;

import dev.santhoshkalisamy.cloudbeestrainreservation.entity.UserDetails;

public interface UserService {
    public UserDetails getUserByEmailId(String emailId);
    public UserDetails getUserById(int id);
    public UserDetails addUser(UserDetails userDetails);
}
