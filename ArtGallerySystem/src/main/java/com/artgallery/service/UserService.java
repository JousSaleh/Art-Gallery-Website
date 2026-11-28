
package com.artgallery.service;

import com.artgallery.model.User;
import com.artgallery.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserRepository repository = new UserRepository();
    private List<User> users;

    public UserService() {
        loadUsers();
    }

    public void loadUsers() {
        users = repository.loadAll();
    }

    public boolean usernameExists(String username) {
        return users.stream()
                .anyMatch(u -> u.getUsername()
                        .equalsIgnoreCase(username));
    }

    public boolean register(User newUser) {

        if (newUser == null
                || usernameExists(newUser.getUsername())) {
            return false;
        }

        List<User> updatedUsers = new ArrayList<>(users);
        updatedUsers.add(newUser);

        repository.saveAll(updatedUsers);

        users = updatedUsers;

        return true;
    }

    public Optional<User> login(String username, String password) {

        return users.stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(username)
                        && u.getPassword().equals(password))
                .findFirst();
    }

    public int countUsers() {
        return users.size();
    }
}