package com.github.elizeuborges.alocadorativos.demo.repository;

import com.github.elizeuborges.alocadorativos.demo.repository.entity.DemoEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class DemoRepository {

    private DemoDAO dao;

    @Transactional
    public DemoEntity salvar(DemoEntity entity) {
        return dao.save(entity);
    }

    public Optional<DemoEntity> buscar(Long id) {
        return dao.findById(id);
    }
}
