package com.basicauth.template.repository;

import com.basicauth.template.AbstractPersistenceTest;
import com.basicauth.template.domain.Role;
import com.basicauth.template.domain.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserRepositoryTest extends AbstractPersistenceTest {

    private UserRepository userRepository;

    @Before
    public void initializeRepository() {
        userRepository = new UserRepository();
        userRepository.em = em;
    }

    @Test
    public void userCanBeRetrievedByUsername(){
        User newUser = new User("Bob","Jan",  "UHxNtYMRYwvfpO1dS4pWLKL0M3DgOj30EbN4SoBWgfc=", "Lol@Bolcom", Role.USER);
        userRepository.em.persist(newUser);

        User userFromDb = userRepository.findByEmail(newUser.getEmail());
        assertNotNull(newUser.getId());

        assertEquals(userFromDb.getFirstname(), newUser.getFirstname());
        assertEquals(userFromDb.getLastname(), newUser.getLastname());
        assertEquals(userFromDb.getPassword(), newUser.getPassword());
        assertEquals(userFromDb.getEmail(), newUser.getEmail());
        assertEquals(userFromDb.getRole(), newUser.getRole());
    }
}