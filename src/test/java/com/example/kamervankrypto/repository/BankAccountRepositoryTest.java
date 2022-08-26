package com.example.kamervankrypto.repository;

import com.example.kamervankrypto.model.BankAccount;
import com.example.kamervankrypto.model.Trader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BankAccountRepositoryTest {

    private BankAccountRepository bankAccountRepositoryTested;
    private BankAccountDAO mockBankAccountDAO;
    private Trader mockTrader;

    @BeforeAll
    void setup() throws ParseException {
        mockBankAccountDAO = Mockito.mock(BankAccountDAO.class);

        //List<Trader> mockTraderList = new ArrayList<>();
        String sBirthDate = "11/01/1990";
        Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(sBirthDate);

        LocalDateTime dateNow = LocalDateTime.now();
        DateTimeFormatter DATEFORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        mockTrader = new Trader(1, "a@b.com", "Password", "Piet1", "de", "Weetikniet1", 123456789, birthDate, "Straat", "5a", "1234ab", "Stadje", true);
        BankAccount mockBankAccount = new BankAccount(12345.25, LocalDateTime.parse(dateNow.format(DATEFORMAT), DATEFORMAT), "NL23KVKB356985759");

        Mockito.when(mockBankAccountDAO.getBankAccount(1)).thenReturn(mockBankAccount);
        bankAccountRepositoryTested = new BankAccountRepository(mockBankAccountDAO);
    }

    @Test
    void test_build_of_BankAccount1() {
        BankAccount testBankAccount = bankAccountRepositoryTested.getBankAccount(mockTrader);

        System.out.println(testBankAccount.toString());
        assertThat(testBankAccount).isNotNull().isInstanceOf(BankAccount.class);

    }


}
