package dev.santhoshkalisamy.cloudbeestrainreservation.service;

import dev.santhoshkalisamy.cloudbeestrainreservation.entity.UserDetails;

/**
 * This is the service interface for the UserDetails entity.
 * It declares methods for user-related operations.
 */
public interface UserService {

    /**
     * This method is used to get a user by their email ID.
     * @param emailId The email ID of the user.
     * @return The UserDetails entity that matches the given email ID.
     */
    public UserDetails getUserByEmailId(String emailId);

    /**
     * This method is used to add a new user.
     * @param userDetails The UserDetails entity to be added.
     * @return The added UserDetails entity.
     */
    public UserDetails addUser(UserDetails userDetails);
}
