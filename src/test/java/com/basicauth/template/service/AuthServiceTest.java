package com.basicauth.template.service;

import com.basicauth.template.domain.Role;
import com.basicauth.template.domain.User;
import com.basicauth.template.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthServiceBean authServiceBean;

    @Before
    public void setupMox() {
        String email = "lol@bol.com";
        User user = new User("Bob","Jan",  "UHxNtYMRYwvfpO1dS4pWLKL0M3DgOj30EbN4SoBWgfc=", email, Role.USER);
        when(userRepository.findByEmail(email)).thenReturn(user);
    }

    @After
    public void tearDown() throws Exception {
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void shouldAuthenticateUser() {
        User user = new User();
        user.setEmail("lol@bol.com");
        user.setPassword("password");
        User currentUser = authServiceBean.getAuthenticatedUser(user);
        assertNotNull(currentUser);
        verify(userRepository).findByEmail("lol@bol.com");

    }

    @Test
    public void shouldNotAuthenticateUserWithWrongPassword() {
        User user = new User();
        user.setEmail("lol@bol.com");
        user.setPassword("wrong password");
        User currentUser = authServiceBean.getAuthenticatedUser(user);
        assertNull(currentUser);
        verify(userRepository).findByEmail("lol@bol.com");

    }

    @Test
    public void shouldNotAuthenticateUserWithWrongEmail() {
        User user = new User();
        user.setEmail("loooool@bol.com");
        user.setPassword("password");
        User currentUser = authServiceBean.getAuthenticatedUser(user);
        assertNull(currentUser);
        verify(userRepository).findByEmail("loooool@bol.com");

    }

    @Test
    public void shouldNotAuthenticateUserWithWrongEmailAndWrongPassword() {
        User user = new User();
        user.setEmail("loool@bol.com");
        user.setPassword("passwo");
        User currentUser = authServiceBean.getAuthenticatedUser(user);
        assertNull(currentUser);
        verify(userRepository).findByEmail("loool@bol.com");

    }
}