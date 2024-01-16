package com.github.elizeuborges.alocadorativos.demo.api;


import com.github.elizeuborges.alocadorativos.demo.api.dto.CriarDemoDTO;
import com.github.elizeuborges.alocadorativos.demo.api.dto.DemoDTO;
import com.github.elizeuborges.alocadorativos.demo.repository.DemoRepository;
import com.github.elizeuborges.alocadorativos.demo.repository.entity.DemoEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@AllArgsConstructor
@RestController
@RequestMapping("/demo")
public class DemoController {

    private DemoRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<DemoDTO> buscarPorId(@PathVariable Long id) {
        return repository.buscar(id)
                .map(this::convert)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound()
                        .build());
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public DemoDTO criar(@RequestBody CriarDemoDTO dto) {
        return convert(repository.salvar(new DemoEntity(dto.getDate())));
    }

    private DemoDTO convert(DemoEntity demoEntity) {
        return new DemoDTO(
                demoEntity.getId(),
                demoEntity.getData()
        );
    }

}
