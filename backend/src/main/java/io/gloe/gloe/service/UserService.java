package io.gloe.gloe.service;

import io.gloe.gloe.entity.User;

public interface UserService {
    User registerUser(String email, String rawPassword, User.Role role);

    boolean validateCredentials(String email, String password);
}
