package com.crud_read_and_create.mapper;

import com.crud_read_and_create.entity.Game;
import com.crud_read_and_create.entity.GamePlatform;
import com.crud_read_and_create.entity.Platform;
import com.crud_read_and_create.service.OrderBy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface GameMapper {

    public List<Game> findAll(@Param("orderBy") OrderBy orderBy);

    public Optional<Game> findById(Integer id);

    public List<Platform> findPlatform();

    public void createGame(Game game);

    public void createGamePlatform(@Param("gamePlatformList") List<GamePlatform> gamePlatformList);

    public void createPlatform(Platform platformData);

    public void updateGame(Game game);

    public void updatePlatform(Platform platformData);

    public void deleteGameAndGamePlatform(Integer id);

    public void deleteGame(Integer id);

    public void deleteGamePlatformGameId(Integer id);

    public void deleteGamePlatformPlatformId(Integer id);

    public void deletePlatformAndGamePlatform(Integer id);

    public void deletePlatform(Integer id);
}
