package com.example.ECommerce.ECommercepro.ServiceTest;

import com.example.ECommerce.ECommercepro.Model.Users;
import com.example.ECommerce.ECommercepro.Repository.UsersRepo;
import com.example.ECommerce.ECommercepro.Service.UsersService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @InjectMocks
    private UsersService userService;

    @Mock
    private UsersRepo userRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser_Success() {
        Users user = new Users();
        user.setId("user123");
        user.setEmail("test@example.com");
        user.setPassword("encryptedPassword");

        when(userRepository.save(any(Users.class))).thenReturn(user);

        Users savedUser = userService.registerUsers(user);

        assertNotNull(savedUser);
        assertEquals("user123", savedUser.getId());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testGetUserByEmail_Found() {
        String email = "test@example.com";

        Users user = new Users();
        user.setId("user123");
        user.setEmail(email);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        Users foundUser = userService.getUserByEmail(email);

        assertNotNull(foundUser);
        assertEquals(email, foundUser.getEmail());
    }

    @Test
    public void testGetUserByEmail_NotFound() {
        String email = "missing@example.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.getUserByEmail(email));
    }
}
