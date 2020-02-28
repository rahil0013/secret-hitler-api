package com.secrethitler.controller;

import com.secrethitler.repository.GameRepository;
import com.secrethitler.repository.AdminRepository;
import com.secrethitler.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PlayerController {

  @Autowired
  private AdminRepository adminRepository;

  @Autowired
  private GameRepository gameRepository;

  @Autowired
  private GameService gameService;

  private static final Logger logger = LoggerFactory.getLogger(PlayerController.class);


}