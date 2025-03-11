package id.my.hendisantika.gcpspanner.controller;

import com.google.common.collect.Lists;
import id.my.hendisantika.gcpspanner.entity.User;
import id.my.hendisantika.gcpspanner.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
