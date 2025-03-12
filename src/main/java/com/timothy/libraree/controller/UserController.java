package com.timothy.libraree.controller;

import com.timothy.libraree.model.UserRequest;
import com.timothy.libraree.model.UserResponse;
import com.timothy.libraree.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping()
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest req) {
        log.info("Req: " + req);
        UserResponse res = userService.createUser(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
}
