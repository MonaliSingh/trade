package com.monali.deutsche.service;

import com.monali.deutsche.dao.Trade;
import com.monali.deutsche.dao.TradeIdNo;
import com.monali.deutsche.dao.TradeRepository;
import com.monali.deutsche.model.TradeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class for trade application
 *
 * @author  Monali Singh
 * @version 1.0
 * @since   2021-05-02
 */

@Service
public class TradeServiceImpl implements TradeService{

    @Autowired
    private TradeRepository tradeRepository;

    @Override
    public List<TradeModel> findTrades() {
        List<TradeModel> t = new ArrayList<TradeModel>();
        List<Trade> t1 = tradeRepository.findAll();
        for(Trade trade:t1)
        {
            t.add(mapToModel(trade));
        }
        return t;
    }

    @Override
    public void create(TradeModel tradeModel) {
        tradeRepository.save(mapToDao(tradeModel));
    }

    private TradeModel mapToModel(Trade t){
        TradeModel tm = new TradeModel();

        tm.setTradeId(t.getTradeIdNo().getTradeId());
        tm.setVersion(t.getTradeIdNo().getVersion());
        tm.setCounterPartyId(t.getCounterPartyId());
        tm.setBookId(t.getBookId());
        tm.setMaturityDate(t.getMaturityDate());
        tm.setCreatedDate(t.getCreatedDate());
        tm.setExpired(t.getExpired());

        return tm;
    }

    private Trade mapToDao(TradeModel tm) {
        Trade t = new Trade();
        TradeIdNo t1 = new TradeIdNo();

        t1.setTradeId(tm.getTradeId());
        t1.setVersion(tm.getVersion());

        t.setTradeIdNo(t1);
        t.setCounterPartyId(tm.getCounterPartyId());
        t.setBookId(tm.getBookId());
        t.setMaturityDate(tm.getMaturityDate());
        t.setCreatedDate(tm.getCreatedDate());
        t.setExpired(tm.getExpired());

        return t;
    }

    //Validates maturity date with current date
    public boolean validateDate(TradeModel tradeModel)
    {
       int i = tradeModel.getMaturityDate().compareTo(LocalDate.now());
       if (i<0)
           return false;
       else
           return true;
    }

    //Validates the incoming Trade version with the existing data
    public boolean validateVersion(TradeModel tradeModel)
    {
        Integer version = tradeRepository.findMaxVersion(tradeModel.getTradeId());
        if (version == null)
            return true;
        else {
            if (tradeModel.getVersion() >= version)
                return true;
            else
                return false;
        }
    }

    public void updateExpired(String flag, LocalDate now) {
        tradeRepository.updateExpired(flag,now);
    }
}
