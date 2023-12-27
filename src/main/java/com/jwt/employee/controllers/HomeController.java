package com.jwt.employee.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.jwt.employee.models.User;
import com.jwt.employee.service.UserService;

@RestController
@RequestMapping("/home")
public class HomeController 
{
	
 @Autowired
 private UserService userService;
	
	//  http://localhost:7071/api/s1/users
	@GetMapping("/users")
	public List<User> getUser()
	{
		System.out.println("Getting current users");
		return userService.getUsers();
	}
	
	@GetMapping("/current-user")
	public String getLoggedInUser(Principal principal)
	{
	  return principal.getName();
	}
	
	@PostMapping("/create-user")
	public User createUser(@RequestBody User user)
	{
		return userService.createUser(user);
	}
	@GetMapping("/user/{id}")
	public User getUserById(@PathVariable Long id)
	{
		return userService.getUserById(id);
	}
	@PutMapping("/update-user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id,@RequestBody User user)
	{
		return userService.updateUser(id,user);
	}
	@DeleteMapping("/delete-user/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id)
	{
		return userService.deleteUser(id);
	}
	
}
