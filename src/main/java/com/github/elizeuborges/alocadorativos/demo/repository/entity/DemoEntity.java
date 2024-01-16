package com.github.elizeuborges.alocadorativos.demo.repository.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "DEMO")
@Entity
@Data
@NoArgsConstructor
public class DemoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime data;

    public DemoEntity(LocalDateTime data) {
        this.data = data;
    }
}
