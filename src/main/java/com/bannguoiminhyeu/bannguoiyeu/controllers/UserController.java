package com.bannguoiminhyeu.bannguoiyeu.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bannguoiminhyeu.bannguoiyeu.models.Users;


@Controller
public class UserController {
   private List<Users> usersList = new ArrayList<>();


   public UserController() {
       Users u1 = new Users("1", "Nguyen Van A", "a@donga.edu");
       Users u2 = new Users("2", "Nguyen Van B", "b@donga.edu");
       usersList.add(u1);
       usersList.add(u2);
   }
   
   //1. API get all users
   @GetMapping("/user")
   @ResponseBody
   public List<Users> getUsersList() {
       return usersList;
   }


   //2. API get users by id
   @GetMapping("users/{id}")
   public ResponseEntity<Users> getUserById(@PathVariable("id") String userId) {
       // Không cần @ResponseBody vì ResponseEntity đã bao gồm body
       for (Users user : usersList) {
           if (user.getId().equals(userId)) {
               return ResponseEntity.status(200).body(user);
           }
       }
       return ResponseEntity.status(404).body(null);  // Trả về lỗi 404 nếu không tìm thấy
   }


   //3. API delete user by id
   @DeleteMapping("users/{id}")
   @ResponseBody
   public List<Users> deleteUser(@PathVariable("id") String userId) {
       for (Users user : usersList) {
           if (user.getId().equals(userId)) {
               usersList.remove(user);
               break;
           }
       }
       return usersList;
   }


   //4. API POST new user
   // CREATE: Thêm một User mới (POST /users)
   @PostMapping("/user")
   public ResponseEntity<Users> createUser(@RequestBody Users newuser) {
       usersList.add(newuser);
       return ResponseEntity.status(201).body(newuser);
   }


   //5. API PUT user by id
   @PutMapping("/users/{id}")
   public ResponseEntity<Users> updateUser(@PathVariable("id") String userId, @RequestBody Users updateUser) {
       for (Users user : usersList) {
           if (user.getId().equals(userId)) {
               user.setName(updateUser.getName());
               user.setEmail(updateUser.getEmail());


               return ResponseEntity.status(200).body(user);
           }
       }


       return ResponseEntity.status(404).body(null);
   }
}

