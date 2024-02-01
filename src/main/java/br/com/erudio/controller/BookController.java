package br.com.erudio.controller;

import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.service.BookService;
import br.com.erudio.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book/v1")
@Tag(name = "Book", description = "Endpoints for Managing Books")
public class BookController {

    @Autowired
    BookService service;

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_YAML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Finds a Book",
            responses = {
                    @ApiResponse(description = "Succes", responseCode = "200",
                            content =
                            @Content(schema = @Schema(implementation = BookVO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content()),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content()),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content()),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content())}
    )
    public ResponseEntity<BookVO> findById(@PathVariable(value = "id") Long id) {
        var response = service.findById(id);
    return ResponseEntity.ok(response);
    }

    @GetMapping(produces = {MediaType.APPLICATION_YAML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Finds All Books",
            responses = {
                    @ApiResponse(description = "Succes", responseCode = "200",
                            content =
                            @Content(schema = @Schema(implementation = BookVO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content()),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content()),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content()),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content())}
    )
    public ResponseEntity<List<BookVO>> findAll() {
        var response = service.findAll();
    return ResponseEntity.ok(response);
    }

    @PostMapping(produces = {MediaType.APPLICATION_YAML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Add new Book",
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201",
                            content =
                            @Content(schema = @Schema(implementation = BookVO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content()),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content()),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content()),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content())}
    )
    public ResponseEntity<BookVO> created(@RequestBody BookVO book) {
        var response = service.create(book);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(produces = {MediaType.APPLICATION_YAML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Add new Book",
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201",
                            content =
                            @Content(schema = @Schema(implementation = BookVO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content()),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content()),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content()),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content())}
    )
    public ResponseEntity<BookVO> update(@RequestBody BookVO book) {
        var response = service.update(book);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_YAML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Delete a Book",
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204",
                            content =
                            @Content(schema = @Schema(implementation = BookVO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content()),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content()),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content()),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content())}
    )
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
