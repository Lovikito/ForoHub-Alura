package com.ForoAPI.ForoHub.domain.usuarios.foro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserForoService {

    @Autowired
    private UserForoRepository userRepository;

    public List<UserForo> getAllUsers() {
        return userRepository.findAll();
    }

    public UserForo getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserForo createUser(DatosUserForo dato) {
        UserForo user = new UserForo(dato.id(),
        		dato.email(),
        		dato.userName(),
        		dato.password());
        return userRepository.save(user);
    }
    public UserForo updateUser(Long id, DatosUserForo dato) {
        Optional<UserForo> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()){
            UserForo user = existingUser.get();
            user.setId(dato.id());
            user.setEmail(dato.email());
            user.setUserName(dato.userName());
            user.setPassword(dato.password());
            return userRepository.save(user);
        }
        return null;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
