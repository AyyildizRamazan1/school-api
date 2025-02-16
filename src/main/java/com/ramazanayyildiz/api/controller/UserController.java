package com.ramazanayyildiz.api.controller;

import com.ramazanayyildiz.api.entity.User;
import com.ramazanayyildiz.api.entity.enums.Role;
import com.ramazanayyildiz.api.service.IUserService;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    ResponseEntity<Page<User>> getUsers(@RequestParam(defaultValue = "0") Integer page,
                                        @RequestParam(defaultValue = "10") Integer pageSize) {
        return ResponseEntity.ok(userService.getAll(PageRequest.of(page,pageSize, Sort.by("id"))));
    }

    @GetMapping("/by-role")
    ResponseEntity<List<User>> getUsersByRole(@RequestParam Role role) {
        return ResponseEntity.ok(userService.getUsersByRole(role));
    }

    @GetMapping("/{id}")
    ResponseEntity<User> getUser(@PathVariable Integer id){
        return ResponseEntity.ok(userService.getById(id));
    }

    @PostMapping
    ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.ok(userService.save(user));
    }
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable Integer id){
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
