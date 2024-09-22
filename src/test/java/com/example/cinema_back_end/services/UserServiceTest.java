package com.example.cinema_back_end.services;

import com.example.cinema_back_end.dtos.MovieDTO;
import com.example.cinema_back_end.entities.Role;
import com.example.cinema_back_end.entities.User;
import com.example.cinema_back_end.security.repo.IUserRepository;
import com.example.cinema_back_end.security.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

/**
 * @author tritcse00526x
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private IUserRepository userRepository;
    @InjectMocks
    private UserService userService;

    private User mockUser = new User();
    private Set<Role> roles = new HashSet<>();
    @BeforeEach
    public void setUp() {
        roles.add(new Role());
        mockUser.setId(1);
        mockUser.setPassword("123456");
        mockUser.setUsername("testcase@gmail.com");
        mockUser.setRoles(roles);
        mockUser.setFullName("Test case");
        mockUser.setPhone("0933048894");
    }
    @AfterEach
    public void tearDown() {
        mockUser = null;
    }

    @Test
    public void findByUsername_validUsername_success() {
        // 1. create mock data
        String username = "testcase@gmail.com";
        // 2. define behavior of Repository
        when(userRepository.findByUsername(username)).thenReturn(Optional.ofNullable(mockUser));
        // 3. call service method
        User actualUser = userService.findByUsername(username).get();
        // 4. assert the result
        assertThat(actualUser).isNotNull();
        assertThat(actualUser.getUsername()).isEqualTo("testcase@gmail.com");
        // 4.1 ensure repository is called
        verify(userRepository).findByUsername(username);
    }

}
