package dev.alexgiou.user_service.controller;

import dev.alexgiou.user_service.dto.UserDto;
import dev.alexgiou.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping
  public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
    UserDto createdUser = userService.createUser(user);
    return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
  }


  @GetMapping("/{id}")
  public  ResponseEntity<UserDto> getUserById(@PathVariable Long id){
    UserDto user = userService.getUserById(id);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> updateUser(@PathVariable Long id , @RequestBody UserDto userDto){
   try{
      userService.updateUser(id, userDto);
      return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Error updating user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
   }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id){
    try {
      userService.deleteUser(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
