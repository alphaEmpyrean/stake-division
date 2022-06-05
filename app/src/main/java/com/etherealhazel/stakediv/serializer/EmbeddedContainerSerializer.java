package com.etherealhazel.stakediv.serializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.etherealhazel.stakediv.model.Container;
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
            containersDto.add(new EmbeddedContainerDto(container.getName(), container.getContainerId()));
        }
        gen.writeObject(containersDto);        
    }

    public class EmbeddedContainerDto {

        

        public EmbeddedContainerDto(String name, UUID containerId) {
            this.name = name;
            this.containerId = containerId;
        }

        private String name;

        private UUID containerId;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public UUID getContainerId() {
            return containerId;
        }

        public void setContainerId(UUID containerId) {
            this.containerId = containerId;
        }

    }
    
}
