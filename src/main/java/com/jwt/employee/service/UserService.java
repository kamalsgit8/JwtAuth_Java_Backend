package com.jwt.employee.service;

	import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt.employee.exceptions.ResourceNotFoundException;
import com.jwt.employee.models.User;
import com.jwt.employee.repo.UserRepository;

	@Service
	public class UserService 
	{
		@Autowired
		UserRepository userRepository;
	
		@Autowired
		private PasswordEncoder passwordEncoder;
		
		 public List<User> getUsers()
		 {
			 return userRepository.findAll();
		 }
		 
		public User createUser(User user)
		{
			 user.setPassword(passwordEncoder.encode(user.getPassword()));
			return userRepository.save(user);
		}
		public User getUserById( Long id)
		{
			return userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Id Not Found"));
		}
		public ResponseEntity<User> updateUser(Long id, User user)
		{
			User client = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee Does not Exist for Updation"));
		     client.setFirstName(user.getFirstName());
			client.setLastName(user.getLastName());
			client.setEmail(user.getEmail());
			User updateUser = userRepository.save(client);
			return ResponseEntity.ok(updateUser);
		}
		public ResponseEntity<HttpStatus> deleteUser(long id)
		{
			User client = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee Does not exist"));
			userRepository.delete(client);
			     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	
	} 