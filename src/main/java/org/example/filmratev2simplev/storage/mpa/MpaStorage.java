package org.example.filmratev2simplev.storage.mpa;

import org.example.filmratev2simplev.model.Mpa;

import java.util.Collection;
import java.util.Optional;


public interface MpaStorage {
    Collection<Mpa> getAllMpa();
    Optional<Mpa> getMpaById(int id);
}
