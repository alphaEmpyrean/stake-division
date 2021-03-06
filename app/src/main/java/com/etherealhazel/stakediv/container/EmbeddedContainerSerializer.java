//https://docs.spring.io/spring-boot/docs/2.3.8.RELEASE/reference/html/spring-boot-features.html#boot-features-json-components
package com.etherealhazel.stakediv.container;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class EmbeddedContainerSerializer extends StdSerializer<List<Container>>{

    public EmbeddedContainerSerializer() {
        this(null);
    }

    protected EmbeddedContainerSerializer(Class<List<Container>> t) {
        super(t);
    }

    @Override
    public void serialize(List<Container> containers, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonProcessingException {
        List<EmbeddedContainerDto> containersDto = new ArrayList<>();
        for (Container container : containers) {
            containersDto.add(new EmbeddedContainerDto(container.getName(), container.getUuid()));
        }
        gen.writeObject(containersDto);        
    }    
}
