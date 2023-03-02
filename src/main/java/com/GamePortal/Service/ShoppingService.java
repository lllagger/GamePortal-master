package com.GamePortal.Service;

import com.GamePortal.Entity.Game;
import com.GamePortal.Entity.User;
import com.GamePortal.Repository.IGameRepository;
import com.GamePortal.Repository.IUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ShoppingService {

    private final IGameRepository gameInformationRepository;
    private final IUserRepository userInformationRepository;

    public ShoppingService(IUserRepository userInformationRepository, IGameRepository gameInformationRepository) {
        this.gameInformationRepository = gameInformationRepository;
        this.userInformationRepository = userInformationRepository;
    }

    public void buyGame(Long gameId, Long userId) {
        Game game = gameInformationRepository.findById(gameId).get();
        User user = userInformationRepository.findById(userId).get();
        game.usersGame(user);

        if (user.getUserCredit() > 0 || user.getUserCredit() > game.getGameCost()) {
            user.setUserCredit(user.getUserCredit() - game.getGameCost());
        } else {
            log.warn("Kullanıcının kredi kontrol algoritmasını kontrol ediniz.");
            System.out.println("Kullanıcının yeterli kredisi bulunmamaktadır.");
        }

        gameInformationRepository.save(game);
    }
}
