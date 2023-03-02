package com.GamePortal.Utils;

import com.GamePortal.Entity.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

public class UserSerializer extends JsonSerializer<List<User>> {
    @Override
    public void serialize(List<User> users, JsonGenerator jgen,
                          SerializerProvider provider) throws IOException {
        jgen.writeStartArray();
        for (User user : users) {
            jgen.writeStartObject();
            jgen.writeObjectField("userId", user.getUserId());
            jgen.writeObjectField("userName", user.getUserName());
            jgen.writeObjectField("userCredit", user.getUserCredit());
            jgen.writeObjectField("userPassword", user.getUserCredit());
            jgen.writeEndObject();
        }
        jgen.writeEndArray();
    }

}
