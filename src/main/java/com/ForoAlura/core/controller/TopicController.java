package com.ForoAlura.core.controller;

import com.ForoAlura.core.dto.topic.TopicDetailedDTO;
import com.ForoAlura.core.dto.topic.TopicRegister;
import com.ForoAlura.core.dto.topic.TopicRegisterResponseDTO;
import com.ForoAlura.core.dto.topic.TopicResponseDTO;
import com.ForoAlura.core.service.ITopicService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/topicos")
public class TopicController {

    @Autowired
    private ITopicService topicService;

    @GetMapping
    public ResponseEntity<Page<TopicResponseDTO>> readAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(this.topicService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDetailedDTO> detailTopic(@PathVariable(value = "id") @NotNull Long id){
        return ResponseEntity.ok(this.topicService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicRegisterResponseDTO> update(@RequestBody @Valid TopicRegister datosRegistro, @PathVariable(value = "id") Long id){
        return ResponseEntity.ok(this.topicService.update(id,datosRegistro));
    }

    @PostMapping
    public ResponseEntity<TopicRegisterResponseDTO> register(@RequestBody @Valid TopicRegister datosRegistro,UriComponentsBuilder uriComponentsBuilder){
        TopicRegisterResponseDTO response =this.topicService.create(datosRegistro);
        URI url = uriComponentsBuilder.path("/topico/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(url).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long topicId){
        this.topicService.delete(topicId);
        return new ResponseEntity<>("Tópico eliminado con éxito", HttpStatus.OK);
    }
}
