package org.example.filmratev2simplev.controller;


import jakarta.validation.Valid;
import org.example.filmratev2simplev.config.AppProperties;
import org.example.filmratev2simplev.dto.MpaDTO;
import org.example.filmratev2simplev.service.mpa.MpaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/mpa")
public class MpaController {

    private final MpaService service;
    private final AppProperties appProperties;

    public MpaController(MpaService service, AppProperties appProperties) {
        this.service = service;
        this.appProperties = appProperties;
    }


    @PostMapping
    public ResponseEntity createMpa(@RequestBody @Valid MpaDTO dto) {
        MpaDTO savedGenre = service.createMpa(dto).orElseThrow(InternalError::new);
        return ResponseEntity.created(
                        URI.create(appProperties.getBaseUrlMpa() + "/" + savedGenre.getId())
                )
                .build();
    }

    @GetMapping
    public ResponseEntity<List<MpaDTO>> getAllMpa() {
        List<MpaDTO> list = service.getAllMpa();
        return ResponseEntity.ok(list);
    }


    @GetMapping("/{id}")
    public ResponseEntity<MpaDTO> getMpaById(@PathVariable Long id) {
        MpaDTO mpaDTO = service.getMpaById(id).orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(mpaDTO);
    }

}
