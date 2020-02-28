package com.secrethitler.controller;

import com.secrethitler.exception.AppException;
import com.secrethitler.model.Admin;
import com.secrethitler.model.Role;
import com.secrethitler.model.RoleType;
import com.secrethitler.payload.ApiResponse;
import com.secrethitler.payload.JwtAuthenticationResponse;
import com.secrethitler.payload.LoginRequest;
import com.secrethitler.payload.SignUpRequest;
import com.secrethitler.repository.RoleRepository;
import com.secrethitler.repository.AdminRepository;
import com.secrethitler.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  AdminRepository adminRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  JwtTokenProvider tokenProvider;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateAdmin(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
    new UsernamePasswordAuthenticationToken(
    loginRequest.getUsernameOrEmail(),
    loginRequest.getPassword()
    )
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = tokenProvider.generateToken(authentication);
    return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerAdmin(@Valid @RequestBody SignUpRequest signUpRequest) {
    if(adminRepository.existsByUsername(signUpRequest.getUsername())) {
      return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
      HttpStatus.BAD_REQUEST);
    }

    if(adminRepository.existsByEmail(signUpRequest.getEmail())) {
      return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
      HttpStatus.BAD_REQUEST);
    }

    // Creating admin's account
    Admin admin = new Admin(signUpRequest.getName(), signUpRequest.getUsername(),
    signUpRequest.getEmail(), signUpRequest.getPassword());

    admin.setPassword(passwordEncoder.encode(admin.getPassword()));

    Role userRole = roleRepository.findByType(RoleType.ROLE_ADMIN)
    .orElseThrow(() -> new AppException("Admin Role not set."));

    admin.setRoleId(userRole.getId());

    Admin result = adminRepository.save(admin);

    URI location = ServletUriComponentsBuilder
    .fromCurrentContextPath().path("/api/users/{username}")
    .buildAndExpand(result.getUsername()).toUri();

    return ResponseEntity.created(location).body(new ApiResponse(true, "Admin registered successfully"));
  }
}