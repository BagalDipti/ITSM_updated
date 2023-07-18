package com.itsm.controller;



import javax.naming.AuthenticationException;
import javax.naming.directory.AttributeModificationException;

import com.itsm.config.AppUser;
import com.itsm.config.LoggedInUser;
import com.itsm.config.TokenProvider;
import com.itsm.model.AuthToken;
import com.itsm.model.LoginUser;
import com.itsm.model.User;
import com.itsm.model.UserDto;
import com.itsm.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;



@RestController
@CrossOrigin("*")
public class UserController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserService mainService;


	@Autowired
	    private AuthenticationManager authenticationManager;

	   @Autowired
	    private TokenProvider jwtTokenUtil;
	   
		@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	    public ResponseEntity<?> generateToken(@RequestBody LoginUser loginUser) throws AuthenticationException {
		   final Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        loginUser.getUserName(),
	                        loginUser.getLoginPassword()
	                )
	        );
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        final String token = jwtTokenUtil.generateToken(authentication);
	        return ResponseEntity.ok(new AuthToken(token));
	    }

	    @RequestMapping(value="/register", method = RequestMethod.POST)
	    public User saveUser(@RequestBody UserDto user){
	    	System.out.println(user);
	        return mainService.save(user);
	    }

	
	
	// To create new Roles

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/admin/createRole")
	public User addRole(@RequestBody User user) {
		logger.info("Creating admin role");
		return this.mainService.addRole(user);
	}

	// To get All Roles

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin/showAllRoles")
	public ResponseEntity<?> getAllRoles() {
		logger.info("Showing all roles");
		return ResponseEntity.ok(this.mainService.getAll());
	}
	
	// To update manager Id for created Role
//	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/setManagerID/{userId}")
	public ResponseEntity<?> updateManagerId(@RequestBody User user, @PathVariable Integer userId,@LoggedInUser AppUser appUser) {
		logger.info("Updating manager id"+userId);
		
		if(appUser.getUser().getId() == userId) {
			return ResponseEntity.ok(this.mainService.setManagerID(user, userId));
		}else {
			return ResponseEntity.badRequest().body("Wrong Credential ");
		}
	}
	
	// To set Login_ID and Login_Passwrd  for created Role
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/setIdAndPassword/{userId}")
	public User updateIdAndPassword(@RequestBody User user, @PathVariable Integer userId) {
		logger.info("ID Password set"+userId);
		return this.mainService.setIdAndPassword(user, userId);
	}
	


}
