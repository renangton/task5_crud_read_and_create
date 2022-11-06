package com.crud.service;

import com.crud.controller.view.GameView;
import com.crud.entity.Game;
import com.crud.entity.GamePlatform;
import com.crud.mapper.GameMapper;
import com.crud.service.exception.NotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService {
  private final GameMapper gameMapper;

  public GameService(GameMapper gameMapper) {
    this.gameMapper = gameMapper;
  }

  public List<GameView> getGames(Integer id, String order) throws NotFoundException {

    List<GameView> gameView;
    if (id == null) {
      List<Game> gameList = gameMapper.findAll(OrderBy.from(order));
      gameView = gameList.stream().map(GameView::new).collect(Collectors.toList());
    } else {
      Optional<Game> gameId = gameMapper.findById(id);
      if (gameId.isEmpty()) {
        throw new NotFoundException("レコードは存在しませんでした。");
      } else {
        gameView = gameId.stream().map(GameView::new).collect(Collectors.toList());
      }
    }
    return gameView;
  }

  @Transactional
  public void createGame(Integer id, String name, String genre, Integer price, String[] platformIds) {
    Game game = new Game(id, name, genre, price);
    gameMapper.createGame(game);
    List<GamePlatform> gamePlatformList = Arrays.stream(platformIds)
        .map(platformId -> new GamePlatform(game.getId(), platformId)).collect(Collectors.toList());
    gameMapper.createGamePlatform(gamePlatformList);
  }

  public void updateGame(Integer id, String name, String genre, Integer price) {
    Game game = new Game(id, name, genre, price);
    gameMapper.updateGame(game);
  }

  @Transactional
  public void deleteGame(Integer id) {
    gameMapper.deleteGamePlatformGameId(id);
    gameMapper.deleteGame(id);
  }
}
