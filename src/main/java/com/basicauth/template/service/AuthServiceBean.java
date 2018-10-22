package com.basicauth.template.service;

import com.basicauth.template.domain.Role;
import com.basicauth.template.domain.User;
import com.basicauth.template.repository.UserRepository;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Base64;
import java.util.List;

@Stateless
@Named
public class AuthServiceBean implements Serializable {

    @Inject
    UserRepository userRepository;

    public List<User> findAllUsersByRole(Role role){
        return userRepository.findAllUsersByRole(role);
    }

    public User getAuthenticatedUser(User user){
        User validUser = null;
        String encoded = null;
        try {
            validUser = userRepository.findByEmail(user.getEmail().trim().toLowerCase());
            encoded = encode(user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return validUser != null && validUser.getPassword().equals(encoded) ? validUser : null;
        }
    }

    private static String encode(String data) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(data.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);

        return Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(data.getBytes("UTF-8")));
    }
}
