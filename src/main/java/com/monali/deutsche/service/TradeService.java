package com.monali.deutsche.service;

import com.monali.deutsche.model.TradeModel;

import java.util.List;

/**
 * @author  Monali Singh
 * @version 1.0
 * @since   2021-05-02
 */

public interface TradeService {

    List<TradeModel> findTrades();

    void create(TradeModel tradeModel);
}
