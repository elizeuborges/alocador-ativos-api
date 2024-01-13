package com.github.elizeuborges.alocadorativos.demo.repository;

import com.github.elizeuborges.alocadorativos.AlocadorAtivosApiApplication;
import com.github.elizeuborges.alocadorativos.demo.repository.entity.DemoEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = { AlocadorAtivosApiApplication.class })
@ActiveProfiles("h2")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@AutoConfigureTestEntityManager
class DemoRepositoryTest {

    @Autowired
    private DemoRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void deveSalvarEntity() {
        DemoEntity entityParaSalvar = new DemoEntity();
        entityParaSalvar.setData(LocalDateTime.now());

        DemoEntity entitySalva = repository.salvar(entityParaSalvar);

        assertNotNull(entitySalva);
        assertNotNull(entitySalva.getId());
        assertEquals(entityParaSalvar.getData(), entitySalva.getData());
    }

    @Test
    @Transactional
    void deveBuscarPorId() {
        DemoEntity entityParaSalvar = new DemoEntity();
        entityParaSalvar.setData(LocalDateTime.now());

        DemoEntity entitySalva = entityManager.persist(entityParaSalvar);

        DemoEntity entityRecuperada = repository.buscar(entitySalva.getId()).orElseThrow();

        assertNotNull(entityRecuperada);
        assertNotNull(entitySalva.getId());
        assertEquals(entitySalva, entityRecuperada);
    }
}