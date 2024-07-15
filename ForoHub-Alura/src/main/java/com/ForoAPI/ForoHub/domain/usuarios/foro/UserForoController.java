package com.ForoAPI.ForoHub.domain.usuarios.foro;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserForoController {
    @Autowired
    private UserForoService userService;

    @GetMapping
    public ResponseEntity<List<UserForo>> getAllUsers() {
        List<UserForo> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserForo> getUserById(@PathVariable Long id) {
        try {
            UserForo user = userService.getUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<UserForo> createUser(@RequestBody DatosUserForo dato) {
        UserForo createdUser = userService.createUser(dato);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserForo> updateUser(@PathVariable Long id, @RequestBody DatosUserForo dato){
        try {
            UserForo userUpdated = userService.updateUser(id, dato);
            return new ResponseEntity<>(userUpdated, HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
