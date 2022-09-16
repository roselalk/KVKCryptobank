package com.example.kamervankrypto.service;

import com.example.kamervankrypto.model.Trader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class TraderServiceTest {

    @Autowired
    public TraderServiceTest(TraderService service) {
        super();
        traderService = service;
    }

    private TraderService traderService;

//    @MockBean
//    private static JdbcTraderDAO mockRepo = Mockito.mock(JdbcTraderDAO.class);
//
//    private TraderService traderService = new TraderService(mockRepo);

//    @BeforeAll
//    public static void testSetup() {
//        Mockito.when(mockRepo.findByRoman("XX")).thenReturn(
//                new RomanNumeral("XX", 20));
//        Mockito.when(mockRepo.findByRoman("MCMXX")).thenReturn(
//                new RomanNumeral("MCMXX", 1920));


    @Test
    public void test_service_available() {
        assertNotNull(traderService);
    }

    @Test
    void get_all_not_null() {
        assertNotNull(traderService.getAll());
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
        assertThat(trader1).isIn(traderService.getAll());
        assertThat(trader2).isIn(traderService.getAll());
        assertThat(trader3).isIn(traderService.getAll());
        assertThat(trader4).isIn(traderService.getAll());
    }

    @Test
    void getById() {
    }

    @Test
    void getByName() {
    }

    @Test
    void save() {
    }

    @Test
    void upate() {
    }

    @Test
    void delete() {
    }
}