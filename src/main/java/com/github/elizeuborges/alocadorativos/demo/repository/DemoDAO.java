package com.github.elizeuborges.alocadorativos.demo.repository;

import com.github.elizeuborges.alocadorativos.demo.repository.entity.DemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoDAO extends JpaRepository<DemoEntity, Long> {
}
