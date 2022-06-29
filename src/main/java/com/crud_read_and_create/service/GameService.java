package com.crud_read_and_create.service;

import com.crud_read_and_create.controller.view.GameView;
import com.crud_read_and_create.entity.Game;
import com.crud_read_and_create.entity.GamePlatform;
import com.crud_read_and_create.mapper.GameMapper;
import com.crud_read_and_create.mapper.PlatformMapper;
import com.crud_read_and_create.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameService {
    private final GameMapper gameMapper;
    private final PlatformMapper platformMapper;

    public GameService(GameMapper gameMapper, PlatformMapper platformMapper) {
        this.gameMapper = gameMapper;
        this.platformMapper = platformMapper;
    }

    public List<GameView> getGames(Integer id, String order) throws NotFoundException {

        List<GameView> gameView = new ArrayList<GameView>();
        if (id == null) {
            List<Game> gameList = gameMapper.findAll(OrderBy.from(order));
            gameView = gameList.stream().map(GameView::new).collect(Collectors.toList());
        } else {
            if (id != null) {
                Optional<Game> gameId = gameMapper.findById(id);
                if (gameId.isEmpty()) {
                    throw new NotFoundException("レコードは存在しませんでした。");
                } else {
                    gameView = gameId.stream().map(GameView::new).collect(Collectors.toList());
                }
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
}
