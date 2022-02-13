package com.crud_read_and_create.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.crud_read_and_create.entity.Game;

@Mapper
public interface GameMapper {

	public List<Game> findAllAsc();

	public List<Game> findAllDesc();

	public Game findById(Game game);

	public int create(Game game);

}
