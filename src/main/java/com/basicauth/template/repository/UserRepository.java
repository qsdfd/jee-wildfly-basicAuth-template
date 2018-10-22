package com.basicauth.template.repository;

import com.basicauth.template.domain.Role;
import com.basicauth.template.domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UserRepository {

    @PersistenceContext
    EntityManager em;

    public User findByEmail(String email) {
        return em.createQuery("select u from User u where LOWER(u.email)=:email", User.class).setParameter("email", email).getSingleResult();
    }

    public List<User> findAllUsers(){
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    public List<User> findAllUsersByRole(Role role){
        return em.createQuery("select u from User u where u.type=:role", User.class).setParameter("role", role).getResultList();
    }

}
