package org.example.filmratev2simplev.service.mpa;

import org.example.filmratev2simplev.dto.MpaDTO;
import org.example.filmratev2simplev.mappers.MpaMapper;
import org.example.filmratev2simplev.repositories.MpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MpaServiceImpl implements MpaService {

    private final MpaRepository mpaRep;
    private final MpaMapper mpaMapper;

    @Autowired
    public MpaServiceImpl(MpaRepository mpaRep, MpaMapper mpaMapper) {
        this.mpaRep = mpaRep;
        this.mpaMapper = mpaMapper;
    }


    @Override
    public Optional<MpaDTO> createMpa(MpaDTO mpaDTO) {
        return Optional.ofNullable(mpaMapper.mpaToMpaDTO(mpaRep.save(mpaMapper.mpaDTOtoMpa(mpaDTO))));
    }

    @Override
    public List<MpaDTO> getAllMpa() {
        return mpaRep.findAll().stream()
                .map(mpaMapper::mpaToMpaDTO)
                .toList();
    }

    @Override
    public Optional<MpaDTO> getMpaById(Long id) {
        return Optional.ofNullable(
                mpaMapper.mpaToMpaDTO(mpaRep.findById(id).orElse(null))
        );
    }
}
