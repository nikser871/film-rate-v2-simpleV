package org.example.filmratev2simplev.controller;


import org.example.filmratev2simplev.model.Mpa;
import org.example.filmratev2simplev.storage.mpa.MpaStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/mpa")
public class MpaController {

    private final MpaStorage mpaStorage;

    @Autowired
    public MpaController(MpaStorage mpaStorage) {
        this.mpaStorage = mpaStorage;
    }

    @GetMapping
    public Collection<Mpa> getAllMpa() {
        return mpaStorage.getAllMpa();
    }


    @GetMapping("/{id}")
    public Optional<Mpa> getMpaById(@PathVariable int id) {
        return mpaStorage.getMpaById(id);
    }

}
