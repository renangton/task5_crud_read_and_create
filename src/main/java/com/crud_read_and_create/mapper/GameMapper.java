package com.crud_read_and_create.mapper;

import com.crud_read_and_create.entity.Game;
import com.crud_read_and_create.entity.GamePlatform;
import com.crud_read_and_create.service.OrderBy;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GameMapper {

  public List<Game> findAll(@Param("orderBy") OrderBy orderBy);

  public Optional<Game> findById(Integer id);

  public void createGame(Game game);

  public void createGamePlatform(@Param("gamePlatformList") List<GamePlatform> gamePlatformList);

  public void updateGame(Game game);

  public void deleteGameAndGamePlatform(Integer id);

  public void deleteGame(Integer id);

  public void deleteGamePlatformGameId(Integer id);
}
