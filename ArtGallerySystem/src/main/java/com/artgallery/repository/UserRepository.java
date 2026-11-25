
package com.artgallery.repository;

import com.artgallery.model.User;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepository {

    private static final String FILE_PATH = "data/users.txt";

    public List<User> loadAll() {
        List<User> users = new ArrayList<>();
        Path path = Paths.get(FILE_PATH);

        if (!Files.exists(path)) {
            return users;
        }

        try {
            List<String> lines = Files.readAllLines(
                    path, StandardCharsets.UTF_8);

            for (String line : lines) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\|", -1);

                if (parts.length != 4) {
                    continue;
                }

                users.add(new User(
                        parts[0],
                        parts[1],
                        parts[2],
                        parts[3]
                ));
            }

        } catch (IOException e) {
            System.out.println("Could not read " + FILE_PATH
                    + ": " + e.getMessage());
        }

        return users;
    }

    public void saveAll(List<User> users) {

        List<String> lines = users.stream()
                .map(u -> u.getUsername() + "|"
                        + u.getPassword() + "|"
                        + u.getFullName() + "|"
                        + u.getEmail())
                .collect(Collectors.toList());

        try {
            Path path = Paths.get(FILE_PATH);

            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }

            Files.write(path, lines, StandardCharsets.UTF_8);

        } catch (IOException e) {
            throw new IllegalStateException(
                    "Could not save " + FILE_PATH, e);
        }
    }
}
