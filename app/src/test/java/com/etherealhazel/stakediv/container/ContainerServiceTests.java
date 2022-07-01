//https://www.youtube.com/watch?v=kXhYu939_5s

package com.etherealhazel.stakediv.container;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = {ContainerService.class, ContainerRepository.class})
public class ContainerServiceTests {

    @Autowired
    public ContainerService containerService;

    @MockBean
    public ContainerRepository containerRepository;

    @Test
    @DisplayName("Create container successfully")
    public void createContainer_None_ReturnNewContainerObject() {

        Container newContainer = new Container();
        when(containerRepository.save(newContainer)).thenReturn(newContainer);

        assertEquals(newContainer, containerService.createContainer(newContainer));
    }

    @Test
    @DisplayName("Get all containers successfully")
    public void getAllContainers_RepoWith3Entities_Return3Objects() {

        when(containerRepository.findAll()).thenReturn(
            Stream.of(
                new Container(), 
                new Container(), 
                new Container()
            ).collect(Collectors.toList()));

        assertEquals(3, containerService.getAllContainers().size());
    }

    @Test
    @DisplayName("Get container by UUID successfully")
    public void getContainer_ContainerExists_ReturnsCorrectContainerObject() {

        Container container = new Container();
        container.setUuid(UUID.randomUUID());
        when(containerRepository.findById(container.getUuid())).thenReturn(Optional.of(container));

        assertEquals(container, containerService.getContainer(container.getUuid()));
    }

    @Test
    @DisplayName("Add child container relationship successfully")
    public void addChildContainer_BothContainersExist_ReturnUpdatedParentContainer() {

        Container parentContainer = new Container(), childContainer = new Container();
        parentContainer.setUuid(UUID.randomUUID());
        childContainer.setUuid(UUID.randomUUID());
        parentContainer.setChildContainers(Stream.of(childContainer).collect(Collectors.toList()));
        List<Container> containersSaveList = Stream.of(parentContainer, childContainer).collect(Collectors.toList());

        when(containerRepository.saveAll(containersSaveList)).thenReturn(containersSaveList);
        when(containerRepository.findById(parentContainer.getUuid())).thenReturn(Optional.of(parentContainer));
        when(containerRepository.findById(childContainer.getUuid())).thenReturn(Optional.of(childContainer));

        assertEquals(parentContainer , containerService.addChildContainer(parentContainer.getUuid(), childContainer.getUuid()));
    }
}
