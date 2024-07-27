package org.example.filmratev2simplev.mappers;

import org.example.filmratev2simplev.dto.MpaDTO;
import org.mapstruct.Mapper;

@Mapper
public interface MpaMapper {

    MpaDTO mpaToMpaDTO(MpaDTO mpaDTO);

    MpaDTO mpaDTOMpaToMpaDTO(MpaDTO mpaDTO);
}
