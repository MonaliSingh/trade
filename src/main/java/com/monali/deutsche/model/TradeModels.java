package com.monali.deutsche.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper class for TradeModel
 *
 * @author  Monali Singh
 * @version 1.0
 * @since   2021-05-02
 */

public class TradeModels {

    private List<TradeModel> tradeModelList;

    public List<TradeModel> getTradeModelList() {
        if (tradeModelList == null) {
            tradeModelList = new ArrayList<>();
        }
        return tradeModelList;
    }

    public void setTradeModelList(List<TradeModel> tradeModelList) {
        this.tradeModelList = tradeModelList;
    }
}
