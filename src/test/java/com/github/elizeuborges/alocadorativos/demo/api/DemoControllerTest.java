package com.github.elizeuborges.alocadorativos.demo.api;

import com.github.elizeuborges.alocadorativos.demo.repository.DemoRepository;
import com.github.elizeuborges.alocadorativos.demo.repository.entity.DemoEntity;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Optional;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class DemoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DemoRepository repository;

    @Test
    void deveCriarDemo() throws Exception {

        DemoEntity entity = new DemoEntity();
        entity.setId(1L);
        entity.setData(LocalDateTime.now());

        when(repository.salvar(any()))
                .thenReturn(entity);

        LocalDateTime data = LocalDateTime.now();
        mockMvc.perform(
                    post("/demo")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{ \"date\": \"" + data.format(ISO_DATE_TIME) + "\" }")
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(entity.getId().intValue())))
                .andExpect(jsonPath("$.date", is(entity.getData().format(ISO_DATE_TIME)))
                );

        ArgumentCaptor<DemoEntity> captor = ArgumentCaptor.forClass(DemoEntity.class);

        verify(repository)
                .salvar(captor.capture());

        assertEquals(data, captor.getValue().getData());
    }

    @Test
    void deveBuscarPorId() throws Exception {

        DemoEntity entity = new DemoEntity();
        entity.setId(1L);
        entity.setData(LocalDateTime.now());

        when(repository.buscar(entity.getId()))
                .thenReturn(Optional.of(entity));

        mockMvc.perform(get("/demo/" + entity.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(entity.getId().intValue())))
                .andExpect(jsonPath("$.date", is(entity.getData().format(ISO_DATE_TIME)))
                );

    }
}