package com.GamePortal.Controller;

import com.GamePortal.Service.ShoppingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/buy")
@Slf4j
public class ShoppingController {

    ShoppingService shoppingService;

    @PutMapping("/{userId}/{gameId}")
    public String userBuyGame(@PathVariable Long gameId, @PathVariable Long userId) {
        shoppingService.buyGame(gameId, userId);
        log.info(String.format("%s user buyed %s game."), userId,gameId);
        return userId.toString() + " kullanıcısı " + gameId.toString() + " Oyununu satın alındı";
    }
}