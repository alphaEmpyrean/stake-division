//https://docs.spring.io/spring-boot/docs/2.3.8.RELEASE/reference/html/spring-boot-features.html#boot-features-json-components
package com.etherealhazel.stakediv.serializer;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.etherealhazel.stakediv.dto.EmbeddedUserDto;
import com.etherealhazel.stakediv.model.AppUser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class EmbeddedUserSerializer extends StdSerializer<Set<AppUser>> {

    public EmbeddedUserSerializer() {
        this(null);
    }

    public EmbeddedUserSerializer(Class<Set<AppUser>> t) {
        super(t);
    }

    @Override
    public void serialize(Set<AppUser> users, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonProcessingException {
        Set<EmbeddedUserDto> usersDto = new HashSet<>();
        for (AppUser user : users) {
            usersDto.add(new EmbeddedUserDto(user.getUsername(), user.getUuid()));
        }
        gen.writeObject(usersDto);        
    }    
}
