package com.github.elizeuborges.alocadorativos.demo.api;


import com.github.elizeuborges.alocadorativos.demo.api.dto.DemoDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping
    public DemoDTO demo() {
        return new DemoDTO(LocalDateTime.now());
    }

}
