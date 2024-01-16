package com.github.elizeuborges.alocadorativos.demo.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class DemoEntity {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime data;

    public DemoEntity(LocalDateTime data) {
        this.data = data;
    }
}
