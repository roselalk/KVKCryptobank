package com.example.kamervankrypto.repository;

import com.example.kamervankrypto.model.Trader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class JdbcTraderDAOTest {

    private TraderDAO traderDAO;

    @Autowired
    public JdbcTraderDAOTest(JdbcTemplate jdbcTemplate) { this.traderDAO = new JdbcTraderDAO(jdbcTemplate); }

    @Test
    void test_service_available() {
        assertNotNull(traderDAO);
    }

    @Test
    void get_all_not_null() {
        assertNotNull(traderDAO.findAll());
    }

    @Test
    void get_all_returns_correct_data() {
//        Trader trader1 = new Trader(1, "1@g.com", "ww", "A", null, "X", 123,
//                "122345", "A St", "1", "1234KL", "Java", true, "*174719sdfA");
//        Trader trader2 = new Trader(2, "2@g.com", "ww", "B", null, "Y", 456,
//                "122345", "B St", "2", "1234KL", "Java", true, "AKJS(128*!");
//        Trader trader3 = new Trader(3, "3@g.com", "ww", "C", null, "Z", 789,
//                "122345", "C St", "3", "1234KL", "Java", true, "*(@SFDJKLK");
        Trader trader1 = new Trader(1, "macca@hotmail.com", "password", "Paul", null, "McCartney", 298791823, "15-05-1942", "Maple St", "15", "83291MA", "Liverpool", true, "SDFKLJ@9824!");
        Trader trader2 = new Trader(2, "johnnieboy@yahoo.com", "wachtwoord", "John", null, "Lennon", 298791822, "31-09-1941", "Oak St", "42", "83291LI", "Liverpool", true, "SDFKLJ@9824!");
        Trader trader3 = new Trader(3, "starr@gmail.com", "pw", "Ringo", null, "Starr", 292391822, "31-10-1944", "Tree St", "8", "82191LI", "Liverpool", true, "SDFKLJ@9824!");
        Trader trader4 = new Trader(4, "awesomeguitarguy@live.nl", "ww", "George", null, "Harrisson", 298791222, "14-03-1945", "Fig St", "9A", "83291PL", "Liverpool", true, "SDFKLJ@9824!");
        assertThat(trader1).isIn(traderDAO.findAll());
        assertThat(trader2).isIn(traderDAO.findAll());
        assertThat(trader3).isIn(traderDAO.findAll());
        assertThat(trader4).isIn(traderDAO.findAll());
    }

    @Test
    void save() {
    }

    @Test
    void findById() {
    }

    @Test
    void getTraderByName() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}