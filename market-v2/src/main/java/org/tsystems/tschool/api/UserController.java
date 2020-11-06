package org.tsystems.tschool.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tsystems.tschool.dto.UserItemDto;
import org.tsystems.tschool.service.UserService;

import java.util.List;

@RestController()
@RequestMapping("/api")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public ResponseEntity<List<UserItemDto>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }
}
