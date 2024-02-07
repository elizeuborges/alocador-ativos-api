package com.github.elizeuborges.alocadorativos.demo.repository;

import com.github.elizeuborges.alocadorativos.demo.api.dto.CriarDemoDTO;
import com.github.elizeuborges.alocadorativos.demo.api.dto.DemoDTO;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Repository
public class DemoJdbcDAO implements DemoDAO {

    @Override
    public DemoDTO salvar(CriarDemoDTO dto) {
        //2024-02-07 20:15:17.792
        String dataComoString = dto.getData().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));

        String sql = "INSERT INTO DEMO (data) VALUES ('" + dataComoString + "')";

        try (Connection connection = createConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.execute();

            ps.getGeneratedKeys().next();
            Long idGerado = ps.getGeneratedKeys().getLong(1);

            return new DemoDTO(idGerado, dto.getData());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<DemoDTO> buscarPorId(Long id) {
        String sql = "SELECT * FROM DEMO WHERE id = " + id;

        try (Connection connection = createConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Long idRetornado = rs.getLong("ID");
                LocalDateTime data = rs.getTimestamp("DATA")
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();

                DemoDTO dadoRecuperado = new DemoDTO(idRetornado, data);

                return Optional.of(dadoRecuperado);
            } else {
                return Optional.empty();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection createConnection() {
        String url = "jdbc:h2:file:~/alocador-ativos-db;DB_CLOSE_DELAY=-1;MODE=MYSQL;AUTO_SERVER=TRUE";
        String user = "sa";
        String password = "sa";

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter conexão com banco de dados.", e);
        }
    }

}
