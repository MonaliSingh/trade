package com.monali.deutsche.controller;


import com.monali.deutsche.model.TradeModel;
import com.monali.deutsche.model.TradeModels;
import com.monali.deutsche.service.TradeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for TradeController
 *
 * @author  Monali Singh
 * @version 1.0
 * @since   2021-05-02
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class TradeControllerTest {

    private final String TRADE_ID = "T1";
    private final Integer VERSION = 1;
    private final String COUNTER_PARTY_ID = "CP1";
    private final String BOOK_ID = "B1";
    private final LocalDate MATURITY_DATE = LocalDate.of(2021,06,01);
    private final LocalDate CURRENT_DATE = LocalDate.now();
    private final String EXPIRED = "N";

    @Autowired
    private TradeController tradeController;

    @MockBean
    private TradeServiceImpl tradeService;

    @Test
    public void testGetAllTrade() throws Exception{
        List<TradeModel> tradeList = new ArrayList<>();
        tradeList.add(getTrade());
        TradeModels received = new TradeModels();
        Mockito.when(tradeService.findTrades()).thenReturn(tradeList);
        received = tradeController.getAllTrades();
        assertThat(received.getTradeModelList().size()).isEqualTo(1);
        assertThat(received.getTradeModelList().get(0).getTradeId()).isEqualTo("T1");
    }

    private TradeModel getTrade(){
        TradeModel tradeModel = new TradeModel();
        tradeModel.setTradeId(TRADE_ID);
        tradeModel.setVersion(VERSION);
        tradeModel.setCounterPartyId(COUNTER_PARTY_ID);
        tradeModel.setBookId(BOOK_ID);
        tradeModel.setMaturityDate(MATURITY_DATE);
        tradeModel.setCreatedDate(CURRENT_DATE);
        tradeModel.setExpired(EXPIRED);
        return tradeModel;

    }
}