package com.etherealhazel.stakediv.serializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.etherealhazel.stakediv.model.AppUser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class EmbeddedUserSerializer extends StdSerializer<List<AppUser>> {

    public EmbeddedUserSerializer() {
        this(null);
    }

    public EmbeddedUserSerializer(Class<List<AppUser>> t) {
        super(t);
    }

    @Override
    public void serialize(List<AppUser> users, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonProcessingException {
        List<EmbeddedUserDto> usersDto = new ArrayList<>();
        for (AppUser user : users) {
            usersDto.add(new EmbeddedUserDto(user.getUsername(), user.getUserId()));
        }
        gen.writeObject(usersDto);        
    }

    public class EmbeddedUserDto {

        
        public EmbeddedUserDto(String username, UUID id) {
            this.username = username;
            this.id = id;
        }

        private String username;

        private UUID id;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        
    }

    
}
