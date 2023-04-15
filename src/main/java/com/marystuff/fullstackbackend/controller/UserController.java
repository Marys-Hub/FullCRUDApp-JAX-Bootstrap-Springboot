package com.marystuff.fullstackbackend.controller;

import com.marystuff.fullstackbackend.exception.UserNotFoundException;
import com.marystuff.fullstackbackend.model.User;
import com.marystuff.fullstackbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
//The @RestController annotation is used in Spring MVC
// to mark a class as a RESTful controller.
//When a class is annotated with @RestController,
// it indicates that the class is a specialized version
// of the @Controller annotation and is responsible for
// handling HTTP requests and returning JSON, XML or other
// data formats representing the response. This is in contrast
// to a traditional @Controller class, which typically returns
// a view name that is resolved by a view resolver to generate HTML.
@CrossOrigin("http://localhost:3000") //asa imi va pune in tabel
public class UserController {
    // is used for automatic dependency injection.
    // Spring framework is built on dependency injection and
    // we inject the class dependencies through spring bean configuration file.
    @Autowired
    private UserRepository userRepository;

    //for mapping the data into the database
    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }

    //getMapping to get user data from database
    @GetMapping("/users")
    List<User> getAllUsers(){
        return userRepository.findAll(); //JPA implemented method
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id){
        return userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException(id));

    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id){
        return userRepository.findById(id)
                .map(user ->{
                    user.setUsername(newUser.getUsername());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User with " + id + " has been deleted";
    }




}
