package com.example.demo.Service;

import com.example.demo.Respository.UserRepository;
import com.example.demo.modal.User;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
@RequestMapping(path="/api")
public class MainService {

    @Autowired
    private UserRepository userRepository;

    public @ResponseBody Iterable<User> getAllUser() {
        return userRepository.findAll();
    }

    public @ResponseBody String createNewUser(@RequestParam User user) {
        User n = new User();
        n.setName(user.getName());
        n.setEmail(user.getEmail());
        userRepository.save(n);
        return "Create new user";
    }

    public @ResponseBody String deleteUserById(int id) {
        userRepository.deleteById(id);
        return "Delete User ID :" + id;
    }

    public @ResponseBody String deleteUserByName(String name){
        userRepository.deleteByName(name);
        return "Delete User Name :"+name;
    }


    @Transactional
    public String updateUserById(int id, User user) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            userRepository.save(existingUser);
            return "Updated User: " + existingUser.getId();
        }).orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));
    }
}

