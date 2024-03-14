package dev.santhoshkalisamy.cloudbeestrainreservation.service.impl;

import dev.santhoshkalisamy.cloudbeestrainreservation.entity.UserDetails;
import dev.santhoshkalisamy.cloudbeestrainreservation.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getUserByEmailIdReturnsUserDetails() {
        String emailId = "santhosh@gmail.com";
        UserDetails userDetails = new UserDetails();
        userDetails.setEmail(emailId);

        Mockito.when(userRepository.findUserByEmail(emailId)).thenReturn(userDetails);

        UserDetails result = userService.getUserByEmailId(emailId);

        assertNotNull(result, "UserDetails should not be null");
        assertEquals(emailId, result.getEmail(), "Email should match the provided email");
    }

    @Test
    public void getUserByEmailIdReturnsNullWhenUserNotFound() {
        String emailId = "santhosh@gmail.com";

        Mockito.when(userRepository.findUserByEmail(emailId)).thenReturn(null);

        UserDetails result = userService.getUserByEmailId(emailId);

        assertNull(result, "UserDetails should be null when user is not found");
    }

    @Test
    public void addUserReturnsSavedUserDetails() {
        UserDetails userDetails = new UserDetails();
        userDetails.setEmail("santhosh@gmail.com");

        Mockito.when(userRepository.save(userDetails)).thenReturn(userDetails);

        UserDetails result = userService.addUser(userDetails);

        assertNotNull(result, "Saved UserDetails should not be null");
        assertEquals(userDetails.getEmail(), result.getEmail(), "Email should match the provided email");
    }
}
