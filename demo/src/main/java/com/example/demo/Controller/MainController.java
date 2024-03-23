package com.example.demo.Controller;

import com.example.demo.Respository.UserRepository;
import com.example.demo.Service.MainService;
import com.example.demo.modal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {

    @Autowired
    private MainService mainService;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> showAllUser() {
        // This returns a JSON or XML with the users
        return mainService.getAllUser();
    }
    @PostMapping(path="/add")
    public @ResponseBody String addNewUser(@RequestBody User user) {
       return mainService.createNewUser(user);
    }

    @PutMapping("/update/{id}")
    public @ResponseBody String updateUser(@PathVariable("id") int id,@RequestBody User user){
        return mainService.updateUserById(id,user);
    }

    @DeleteMapping("/delete/{id}")
    public @ResponseBody String deleteById(@PathVariable("id") int id){
        return mainService.deleteUserById(id);
    }

    @DeleteMapping("/delete-by-name/{name}")
    public @ResponseBody String deleteByName(@PathVariable("name") String name){
        return mainService.deleteUserByName(name);

    }
}