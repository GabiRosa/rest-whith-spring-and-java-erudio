package br.com.erudio.controller;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.service.PersonService;
import br.com.erudio.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints for Managing People")
public class PersonController {


    @Autowired
    private PersonService  service;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_YAML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Finds a Person",
    responses = {
            @ApiResponse(description = "succes", responseCode = "200",
                    content =
                          @Content(schema = @Schema(implementation = PersonVO.class))),
            @ApiResponse(description = "No content", responseCode = "204", content = @Content()),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content()),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content()),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content()),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content())}
    )
    public PersonVO findyById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }
    @GetMapping(
            produces = {MediaType.APPLICATION_YAML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Finds All People",
            responses = {
                    @ApiResponse(description = "succes", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PersonVO.class)))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content()),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content()),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content()),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content())}
    )
    public ResponseEntity<List<PersonVO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @CrossOrigin(origins = {"http://localhost:8080", "http://erudio.com.br"})
    @PostMapping(
            produces = {MediaType.APPLICATION_YAML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Add new Person",
            responses = {
                    @ApiResponse(description = "succes", responseCode = "200",
                            content =
                            @Content(schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content()),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content()),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content()),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content())}
    )
    public PersonVO create(@RequestBody PersonVO PersonVO) {
        return service.create(PersonVO);
    }

    @PostMapping(value = "/v2", produces = {MediaType.APPLICATION_YAML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Add new Person",
            responses = {
                    @ApiResponse(description = "succes", responseCode = "200",
                            content =
                            @Content(schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content()),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content()),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content()),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content())}
    )
    public PersonVOV2 createV2(@RequestBody PersonVOV2 person) {
        return service.createV2(person);
    }
    @PutMapping(
            produces = {MediaType.APPLICATION_YAML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Updates a Person",
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content =
                            @Content(schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content()),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content()),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content()),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content())}
    )
    public PersonVO update(@RequestBody PersonVO PersonVO) {
        return service.update(PersonVO);
    }
    @DeleteMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_YAML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Delete a Person",
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204",
                            content =
                            @Content(schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content()),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content()),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content()),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content())}
    )
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
