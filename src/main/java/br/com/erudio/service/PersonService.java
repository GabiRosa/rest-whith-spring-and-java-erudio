package br.com.erudio.service;

import br.com.erudio.controller.PersonController;
import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.ModelMapper;
import br.com.erudio.mapper.custom.PersonMapper;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper mapper;

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public List<PersonVO> findAll() {

        logger.info("Finding all people!");

        var persons = ModelMapper.parseListObjects(repository.findAll(), PersonVO.class);
        persons.stream()
                .forEach(p -> p.add(linkTo(methodOn(PersonController.class).findyById(p.getId())).withSelfRel()));
        return persons;

    }

    public PersonVO findById(Long id) {

        logger.info("Finding one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        var vo =  ModelMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findyById(id)).withSelfRel());
        return vo;
    }

    public PersonVO create(PersonVO person) {

        logger.info("Creating one person!");
        var entity = ModelMapper.parseObject(person, Person.class);
        var vo =  ModelMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findyById(vo.getId())).withSelfRel());
        return vo;
    }

    public PersonVOV2 createV2(PersonVOV2 person) {

        logger.info("Creating one person with v2!");
        var entity = mapper.convertVoToEntity(person);
        var vo = mapper.convertEntityToVo(repository.save(entity));
        return vo;
    }


    public PersonVO update(PersonVO person) {

        logger.info("Updating one person!");

        var entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo =  ModelMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findyById(vo.getId())).withSelfRel());
        return vo;
    }

    public void delete(Long id) {

        logger.info("Deleting one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }
}
