package com.secrethitler.service;

import com.secrethitler.exception.BadRequestException;
import com.secrethitler.payload.GameRequest;
import com.secrethitler.repository.GameRepository;
import com.secrethitler.repository.AdminRepository;
import com.secrethitler.security.UserPrincipal;
import com.secrethitler.util.AppConstants;
import com.secrethitler.model.Admin;
import com.secrethitler.model.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

  @Autowired
  private GameRepository gameRepository;

  @Autowired
  private AdminRepository adminRepository;

  private static final Logger logger = LoggerFactory.getLogger(GameService.class);

  public Game createGame(UserPrincipal userPrincipal, GameRequest gameRequest) {
    Game game = new Game();
    game.setAdmin(new Admin(userPrincipal.getId()));
    game.setName(gameRequest.getName());
    game.setGameHash("TESTEST");

    return gameRepository.save(game);
  }


  private void validatePageNumberAndSize(int page, int size) {
    if(page < 0) {
      throw new BadRequestException("Page number cannot be less than zero.");
    }

    if(size > AppConstants.MAX_PAGE_SIZE) {
      throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
    }
  }

}