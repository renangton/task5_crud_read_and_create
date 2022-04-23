package com.crud_read_and_create.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.crud_read_and_create.entity.Game;
import com.crud_read_and_create.entity.GamePlatform;
import com.crud_read_and_create.entity.Platform;
import com.crud_read_and_create.service.OrderBy;

@Mapper
public interface GameMapper {

	public List<Game> findAll(@Param("orderBy") OrderBy orderBy);

	public Optional<Game> findById(Integer id);

	public List<Platform> findPlatform();

	public Integer createGame(Game game);

	public Integer createGamePlatform(@Param("gamePlatformList") List<GamePlatform> gamePlatformList);

	public Integer createPlatform(@Param("id") String id, @Param("platform") String platform);

}
