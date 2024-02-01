package br.com.erudio.unittests.service;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;
import br.com.erudio.service.PersonService;
import br.com.erudio.unittests.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    MockPerson input;

    @InjectMocks
    private PersonService service;

    @Mock
    private PersonRepository repository;

    @BeforeEach
    void setUpMocks() throws Exception {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() throws Exception {
        List<Person> list = input.mockEntityList();
        when(repository.findAll()).thenReturn(list);

        var people = service.findAll();
        assertNotNull(people);
        assertEquals(14, people.size());

        var personOne = people.get(1);
        assertNotNull(personOne);
        assertNotNull(personOne.getId());
        assertTrue(personOne.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Addres Test1", personOne.getAddress());
        assertEquals("First Name Test1", personOne.getFirstName());
        assertEquals("Last Name Test1", personOne.getLastName());
        assertEquals("Female", personOne.getGender());
    }

    @Test
    void testFindById() throws Exception {
        Person entity = input.mockEntity(1);
        entity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        var result = service.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void testUpdate() throws Exception {
        Person entity = input.mockEntity(1);
        Person persisted = entity;
        persisted.setId(1L);

        PersonVO vo = input.mockVO(1);
        vo.setId(1L);
        when(repository.save(entity)).thenReturn(persisted);
        var result = service.create(vo);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void testCreate() throws Exception {
        Person entity = input.mockEntity(1);
        Person persisted = entity;
        persisted.setId(1L);

        PersonVO vo = input.mockVO(1);
        vo.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(persisted);

        var result = service.update(vo);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }
    @Test
    void testDelete() throws Exception {
        Person entity = input.mockEntity(1);
        entity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        service.delete(1L);
    }
}
