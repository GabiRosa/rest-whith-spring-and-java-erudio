package br.com.erudio.service;

import br.com.erudio.controller.BookController;
import br.com.erudio.controller.PersonController;
import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.ModelMapper;
import br.com.erudio.model.Book;
import br.com.erudio.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
public class BookService {

    @Autowired
    BookRepository repository;

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public BookVO findById(Long id) {

        logger.info("Finding one book");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        var vo =  ModelMapper.parseObject(entity, BookVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findyById(vo.getId())).withSelfRel());
        return vo;
    }

    public List<BookVO> findAll() {

        logger.info("Finding all books!");

        var bookVOS = ModelMapper.parseListObjects(repository.findAll(), BookVO.class);
        bookVOS.stream()
                .forEach(b -> b.add(linkTo(methodOn(BookController.class).findById(b.getId())).withSelfRel()));
        return bookVOS;
    }

    public BookVO create(BookVO book) {

        logger.info("Creating one book!");

        var entity = ModelMapper.parseObject(book, Book.class);
        var vo = ModelMapper.parseObject(repository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findyById(vo.getId())).withSelfRel());
        return vo;
    }

    public BookVO update(BookVO bookVO) {

        logger.info("Updating one book!");

        var entity = repository.findById(bookVO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setAuthor(bookVO.getAuthor());
        entity.setPrice(bookVO.getPrice());
        entity.setTitle(bookVO.getTitle());
        entity.setLaunchDate(bookVO.getLaunchDate());

        var vo =  ModelMapper.parseObject(repository.save(entity), BookVO.class);
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
