package com.secrethitler.repository;

import com.secrethitler.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
  Optional<Admin> findByEmail(String email);

  Optional<Admin> findByUsernameOrEmail(String username, String email);

  List<Admin> findByIdIn(List<Long> userIds);

  Optional<Admin> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}