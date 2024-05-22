package net.cabrasky.table2taste.backend.controller;

import net.cabrasky.table2taste.backend.jwt.JwtResponse;
import net.cabrasky.table2taste.backend.jwt.JwtUtils;
import net.cabrasky.table2taste.backend.jwt.UserDetailsImpl;
import net.cabrasky.table2taste.backend.jwt.dto.AuthRequestDTO;
import net.cabrasky.table2taste.backend.model.User;
import net.cabrasky.table2taste.backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtAuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> authenticateUser(@RequestBody AuthRequestDTO loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		String jwt = jwtUtils.generateJwtToken(authentication);

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername()));
	}
	
	@GetMapping("/userInfo")
	public ResponseEntity<User> authenticateUser() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		return ResponseEntity.ok(userRepository.findById(currentPrincipalName).get());
	}

}
