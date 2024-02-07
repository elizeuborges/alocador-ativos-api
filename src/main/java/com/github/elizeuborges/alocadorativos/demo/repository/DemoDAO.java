package com.github.elizeuborges.alocadorativos.demo.repository;

import com.github.elizeuborges.alocadorativos.demo.api.dto.CriarDemoDTO;
import com.github.elizeuborges.alocadorativos.demo.api.dto.DemoDTO;

import java.util.Optional;

public interface DemoDAO {

    DemoDTO salvar(CriarDemoDTO dto);

    Optional<DemoDTO> buscarPorId(Long id);

}
