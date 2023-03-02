package com.GamePortal.Utils;

import com.GamePortal.Entity.Game;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;


public class GameSerializer extends JsonSerializer<List<Game>> {
    @Override
    public void serialize(List<Game> games, JsonGenerator jgen,
                          SerializerProvider provider) throws IOException {
        jgen.writeStartArray();
        for (Game game : games) {
            jgen.writeStartObject();
            jgen.writeObjectField("gameId", game.getGameId());
            jgen.writeObjectField("gameName", game.getGameName());
            jgen.writeObjectField("gameCost", game.getGameCost());
            jgen.writeEndObject();
        }
        jgen.writeEndArray();
    }

}
