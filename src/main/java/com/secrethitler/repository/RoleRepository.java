package com.secrethitler.repository;

import com.secrethitler.model.Role;
import com.secrethitler.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByType(RoleType roleType);

  Optional<Role> findById(Long id);
}