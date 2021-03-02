package com.tts.userAPI.controller;

import com.tts.userAPI.model.User;
import com.tts.userAPI.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RequestMapping("/user")
@RestController
public class UserController {


    UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @GetMapping("/users")
//    public List<User> getUsers() {
//        return (List<User>) userRepository.findAll();
//    }
    @GetMapping("/users/{id}")
    public Optional<User> getUserById(@PathVariable(value="id") Long id) {
        return userRepository.findById(id);
    }

    @PostMapping("/user")
    public void createUser(@RequestBody User user) {
        userRepository.save(user);
    }

    @PutMapping("/user/{id}")
    public void createUser(@PathVariable(value="id") Long id, @RequestBody User user) {
        userRepository.save(user);
    }

    @DeleteMapping("/user/{id}")
    public void createUser(@PathVariable(value="id") Long id) {
        userRepository.deleteById(id);
    }

    @GetMapping("/users")
    public List<User> getUsers(@RequestParam(value="state", required=false) String state) {
        if (state != null) {
            return (List<User>) userRepository.findByState(state);
        }
        return (List<User>) userRepository.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Void> createUser(@RequestBody @Valid User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.equals("")) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }
}

