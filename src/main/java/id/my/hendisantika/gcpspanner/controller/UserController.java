package id.my.hendisantika.gcpspanner.controller;

import com.google.common.collect.Lists;
import id.my.hendisantika.gcpspanner.entity.User;
import id.my.hendisantika.gcpspanner.exception.ResourceNotFoundException;
import id.my.hendisantika.gcpspanner.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-gcp-spanner
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 11/03/25
 * Time: 07.14
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    // get all users rest API
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return Lists.newArrayList(userRepository.findAll());
    }

    // get all users by name rest API
    @GetMapping("/users/name/{name}")
    public List<User> getAllUsersByName(@PathVariable String name) {
        return userRepository.findByName(name);
    }

    // get all users by country rest API
    @GetMapping("/users/country/{name}")
    public List<User> getAllUsersByCountry(@PathVariable String name) {
        return userRepository.findByCountry(name);
    }

    // create user rest API
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        //Random UUID
        user.setId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    // get user by id rest api
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("User not exist with id :" + id));
        return ResponseEntity.ok(user);
    }
}
