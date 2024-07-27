package org.example.filmratev2simplev.service.mpa;

import org.example.filmratev2simplev.dto.MpaDTO;
import org.example.filmratev2simplev.model.Mpa;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MpaService {

    Optional<MpaDTO> createMpa(MpaDTO mpaDTO);
    List<MpaDTO> getAllMpa();
    Optional<MpaDTO> getMpaById(Long id);
}
