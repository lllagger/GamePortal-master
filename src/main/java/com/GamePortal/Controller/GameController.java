package com.GamePortal.Controller;

import com.GamePortal.Entity.Game;
import com.GamePortal.Service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/add/game")
    public String addGameInformation(@RequestBody Game game) {
        log.info("Game added to data base.");
        return gameService.addGameInformation(game);
    }

    @PutMapping("/update/game")
    public String updateGameInformation(@RequestBody Game game) {
        log.info("Game update from data base.");
        return gameService.updateGameInformation(game);
    }

    @DeleteMapping("/delete/game/{id}")
    public String deleteGameInformation(@PathVariable Long id) {
        log.info("Game deleted from data base.");
        return gameService.deleteGameInformation(id);
    }

    @GetMapping("/get/game/{id}")
    public Game getGameInformation(@PathVariable Long id) {
        log.info("Game has showing.");
        return gameService.getGameInformation(id);
    }
}
