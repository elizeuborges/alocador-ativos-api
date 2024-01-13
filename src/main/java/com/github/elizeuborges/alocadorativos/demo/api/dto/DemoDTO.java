package com.github.elizeuborges.alocadorativos.demo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DemoDTO {

    private Long id;
    private LocalDateTime date;

}
