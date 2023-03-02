package com.GamePortal.Service;

import com.GamePortal.Entity.Game;
import com.GamePortal.Repository.IGameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GameService {

    @Autowired(required = false)
    private IGameRepository gameInformationRepository;

    public String addGameInformation(Game game) {
        gameInformationRepository.save(game);
        log.warn("Kullanıcı veri tabanına kaydedildi");
        return game.getGameId() + " Id numaralı yeni oyun veri tabanına kaydedildi";
    }

    public Game getGameInformation(Long id) {
        return gameInformationRepository.findById(id).get();
    }

    public String deleteGameInformation(Long id) {
        Game game = gameInformationRepository.findById(id).get();
        gameInformationRepository.delete(game);
        log.warn("Kullanıcı veri tabanından silindi");
        return id + " numaralı oyun veri tabanından silindi";
    }

    public String updateGameInformation(Game game) {
        gameInformationRepository.save(game);
        log.warn("Oyun güncellendi.");
        return game.getGameId() + " Id numaralı oyun bilgileri güncellendi";
    }

}