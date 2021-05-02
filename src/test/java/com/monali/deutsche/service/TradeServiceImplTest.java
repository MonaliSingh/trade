package com.monali.deutsche.service;

import com.monali.deutsche.dao.TradeRepository;
import com.monali.deutsche.model.TradeModel;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for TradeService
 *
 * @author  Monali Singh
 * @version 1.0
 * @since   2021-05-02
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class TradeServiceImplTest {
    private final String TRADE_ID = "T1";
    private final Integer VERSION = 1;
    private final String COUNTER_PARTY_ID = "CP1";
    private final String BOOK_ID = "B1";
    private final LocalDate WRONG_MATURITY_DATE = LocalDate.of(2020,06,01);
    private final LocalDate CURRENT_DATE = LocalDate.now();
    private final String EXPIRED = "Y";

    @Autowired
    private TradeServiceImpl tradeService;

    @MockBean
    private TradeRepository tradeRepository;

    @Test
    public void testValidateDate(){
        //returned trade object has a past date as maturity date
        boolean result = tradeService.validateDate(getTrade());
        assertThat(result).isEqualTo(false);
    }

    @Test
    public void testVersion(){
        // assuming db contains version value 2 for tradeId T1
        Mockito.when(tradeRepository.findMaxVersion(TRADE_ID)).thenReturn(2);
        boolean result = tradeService.validateVersion(getTrade()); //provided version is 1
        assertThat(result).isEqualTo(false);

    }


    private TradeModel getTrade(){
        TradeModel tradeModel = new TradeModel();
        tradeModel.setTradeId(TRADE_ID);
        tradeModel.setVersion(VERSION);
        tradeModel.setCounterPartyId(COUNTER_PARTY_ID);
        tradeModel.setBookId(BOOK_ID);
        tradeModel.setMaturityDate(WRONG_MATURITY_DATE);
        tradeModel.setCreatedDate(CURRENT_DATE);
        tradeModel.setExpired(EXPIRED);
        return tradeModel;

    }

}