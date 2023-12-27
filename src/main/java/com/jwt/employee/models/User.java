package com.jwt.employee.models;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Employees")
@ToString
public class User implements UserDetails{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
     
    @Column
    @Size(min = 5, max = 50, message = "Email must be between 5 and 50 characters")
    @Email(message = "Invalid email format")
    private String email;
    
    @Column
    private String login;

    @Column
    private String password;
    
    @Override
  	public Collection<? extends GrantedAuthority> getAuthorities() {
  		// TODO Auto-generated method stub
  		return null;
  	}

  	@Override
  	public String getUsername() {
  		// TODO Auto-generated method stub
  		return this.email;
  	}

  	@Override
  	public boolean isAccountNonExpired() {
  		// TODO Auto-generated method stub
  		return true;
  	}

  	@Override
  	public boolean isAccountNonLocked() {
  		// TODO Auto-generated method stub
  		return true;
  	}

  	@Override
  	public boolean isCredentialsNonExpired() {
  		// TODO Auto-generated method stub
  		return true;
  	}

  	@Override
  	public boolean isEnabled() {
  		// TODO Auto-generated method stub
  		return true;
  	}
    
    
    
    
    
    
    
    
    
    
    
    
}
