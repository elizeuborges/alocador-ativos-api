package com.github.elizeuborges.alocadorativos.demo.repository;

import com.github.elizeuborges.alocadorativos.demo.api.dto.CriarDemoDTO;
import com.github.elizeuborges.alocadorativos.demo.api.dto.DemoDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class DemoRepository {

    private DemoDAO dao;

    public DemoDTO salvar(CriarDemoDTO dto) {
        return dao.salvar(dto);
    }

    public Optional<DemoDTO> buscar(Long id) {
        return dao.buscarPorId(id);
    }
}
