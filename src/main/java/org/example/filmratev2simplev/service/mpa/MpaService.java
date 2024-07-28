package org.example.filmratev2simplev.service.mpa;

import org.example.filmratev2simplev.dto.MpaDTO;
import java.util.List;
import java.util.Optional;

public interface MpaService {

    Optional<MpaDTO> createMpa(MpaDTO mpaDTO);
    List<MpaDTO> getAllMpa();
    Optional<MpaDTO> getMpaById(Long id);
}
