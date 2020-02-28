package com.secrethitler.repository;

import com.secrethitler.model.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
  Optional<Game> findById(Long gameId);

  Page<Game> findByAdminId(Long userId, Pageable pageable);

  List<Game> findByIdIn(List<Long> gameIds);

  List<Game> findByIdIn(List<Long> gameIds, Sort sort);
}