package com.secrethitler.security;

import com.secrethitler.model.Admin;
import com.secrethitler.model.Role;
import com.secrethitler.repository.AdminRepository;
import com.secrethitler.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  AdminRepository adminRepository;

  @Autowired
  RoleRepository roleRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
    // Let people login with either username or email
    Admin admin = adminRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
    .orElseThrow(() ->
    new UsernameNotFoundException("Admin not found with username or email : " + usernameOrEmail)
    );

    Optional<Role> roleType = roleRepository.findById(admin.getRoleId());

    return UserPrincipal.create(admin, roleType.get().getType().toString());
  }

  // This method is used by JWTAuthenticationFilter
  @Transactional
  public UserDetails loadUserById(Long id) {
    Admin admin = adminRepository.findById(id).orElseThrow(
    () -> new UsernameNotFoundException("Admin not found with id : " + id)
    );

    Optional<Role> roleType = roleRepository.findById(admin.getRoleId());

    return UserPrincipal.create(admin, roleType.get().getType().toString());
  }
}