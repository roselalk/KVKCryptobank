package com.example.kamervankrypto.repository;

import com.example.kamervankrypto.model.BankAccount;
import com.example.kamervankrypto.model.Trader;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;
import static org.assertj.core.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BankAccountRepositoryTest {

    private BankAccountRepository bankAccountRepositoryTested;
    private BankAccountDAO mockBankAccountDAO;
    private Trader mockTrader;

    @BeforeAll
    void setup() throws ParseException {
        mockBankAccountDAO = Mockito.mock(BankAccountDAO.class);

        List<Trader> mockTraderList = new ArrayList<>();
        String sBirthDate = "11/01/1990";
        Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(sBirthDate);

        mockTrader = new Trader(1,"a@b.com", "Password", "Piet1", "de", "Weetikniet1", 123456789, birthDate, "Straat", "5a", "1234ab", "Stadje", true );
        BankAccount mockBankAccount = new BankAccount(12345.25, DateTime.toString(), "NL23KVKB356985759" );

        Mockito.when(mockBankAccountDAO.getBankAccount(1)).thenReturn(mockBankAccount);
        bankAccountRepositoryTested = new BankAccountRepository(mockBankAccountDAO);
    }

    @Test
    void test_build_of_BankAccount1(){
        BankAccount testBankAccount = bankAccountRepositoryTested.getBankAccount(mockTrader);

        System.out.println(testBankAccount.toString());
        assertThat(testBankAccount).isNotNull().isInstanceOf(BankAccount.class);

    }



}
