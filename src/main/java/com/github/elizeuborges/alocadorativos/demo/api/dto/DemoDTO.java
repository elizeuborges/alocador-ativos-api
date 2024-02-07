package com.github.elizeuborges.alocadorativos.demo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DemoDTO {

    private Long id;
    private LocalDateTime data;

}
