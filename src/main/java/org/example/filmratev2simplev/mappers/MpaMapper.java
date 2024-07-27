package org.example.filmratev2simplev.mappers;

import org.example.filmratev2simplev.dto.MpaDTO;
import org.example.filmratev2simplev.model.Mpa;
import org.mapstruct.Mapper;

@Mapper
public interface MpaMapper {

    MpaDTO mpaToMpaDTO(Mpa mpa);

    Mpa mpaDTOtoMpa(MpaDTO mpaDTO);
}
