package com.crud_read_and_create.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.crud_read_and_create.entity.Game;
import com.crud_read_and_create.entity.Platform;
import com.crud_read_and_create.form.GameForm;
import com.crud_read_and_create.form.GamePlatformForm;
import com.crud_read_and_create.form.PlatformForm;

@Mapper
public interface GameMapper {

	public List<Game> findAllAsc();

	public List<Game> findAllDesc();

	public List<Game> findById(Game game);

	public List<Platform> findPlatform();

	public Integer createGame(GameForm gameForm);

	public Integer createGamePlatform(GamePlatformForm gamePlatformForm);

	public Integer createPlatform(PlatformForm platformForm);

}
