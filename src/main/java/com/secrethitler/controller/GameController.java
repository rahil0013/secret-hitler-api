package com.secrethitler.controller;

import com.secrethitler.repository.GameRepository;
import com.secrethitler.repository.AdminRepository;
import com.secrethitler.security.CurrentUser;
import com.secrethitler.security.UserPrincipal;
import com.secrethitler.service.GameService;
import com.secrethitler.payload.ApiResponse;
import com.secrethitler.payload.GameRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/games")
public class GameController {

  @Autowired
  private GameRepository gameRepository;

  @Autowired
  private AdminRepository adminRepository;

  @Autowired
  private GameService gameService;

  private static final Logger logger = LoggerFactory.getLogger(GameController.class);

  @PostMapping
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<?> createGame(@Valid @RequestBody GameRequest gameRequest, @CurrentUser UserPrincipal userPrincipal) {

    gameService.createGame(userPrincipal,gameRequest);

    return ResponseEntity.created(ServletUriComponentsBuilder
    .fromCurrentRequest().path("/{gameId}")
    .buildAndExpand(1).toUri())
    .body(new ApiResponse(true, "Game Created Successfully"));
  }

}