package com.example.introduction;

import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private final UserDataRepository userDataRepository;

    @Autowired
    public UserController(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    @GetMapping("/user")
    public List<UserData> getAllUser() {
        return userDataRepository.findAll();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserData> getUserById(@PathVariable Integer userId) {
        Optional<UserData> userData = userDataRepository.findById(userId);
        return ResponseEntity.ok(userData.orElse(null));
    }

    @GetMapping("/user-by-email")
    public UserData getUserByEmail(@RequestParam String email) {
        Optional<UserData> userData = userDataRepository.findByEmail(email);
        return userData.orElse(null);
    }

    @PostMapping("/user")
    public UserData createUser(@Valid @RequestBody CreateUserRequest request) {
        UserData userData = new UserData();
        userData.setFullName(request.getFullName());
        userData.setNickName(request.getNickName());
        userData.setEmail(request.getEmail());
        userData.setAddress(request.getAddress());
        userData.setCity(request.getCity());
        userData.setCreatedBy("admin");
        userData.setUpdatedBy("admin");
        return userDataRepository.saveAndFlush(userData);
    }

    @PostMapping("/user-with-record")
    public ResponseEntity<UserData> createUserWithRecord(@Valid @RequestBody CreateUserRequestRecord request) {
        UserData userData = new UserData();
        userData.setFullName(request.fullName());
        userData.setNickName(request.nickName());
        userData.setEmail(request.email());
        userData.setAddress(request.address());
        userData.setCity(request.city());
        userData.setCreatedBy("admin");
        userData.setUpdatedBy("admin");

        UserData result = userDataRepository.saveAndFlush(userData);
        return ResponseEntity.ok(result);
    }

}
