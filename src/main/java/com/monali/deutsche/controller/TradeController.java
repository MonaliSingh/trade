package com.monali.deutsche.controller;

import com.monali.deutsche.model.TradeModel;
import com.monali.deutsche.model.TradeModels;
import com.monali.deutsche.service.TradeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The TradeController is a RestController for trade application
 *
 * @author  Monali Singh
 * @version 1.0
 * @since   2021-05-02
 */

@RestController
@RequestMapping("/api/trade")
public class TradeController {

    @Autowired
    private TradeServiceImpl tradeService;

    //Fetches all existing trades from database
    @GetMapping("/all")
    public TradeModels getAllTrades(){
        TradeModels response = new TradeModels();
        List<TradeModel> list = new ArrayList<>();
        list = tradeService.findTrades();
        response.setTradeModelList(list);
        return response;

    }

    //End point to insert fresh valid trade
    @PostMapping("/create")
    private void createTrade(@RequestBody TradeModel tradeModel){
        if (tradeService.validateDate(tradeModel)){
            if(tradeService.validateVersion(tradeModel)){
                tradeService.create(tradeModel);

            }
            else
                throw new RuntimeException("Lower Version can not be added");
        }

        else
            throw new RuntimeException("Maturity Date should not be less than Current Date.");
    }

    //Scheduler updates the expired everyday at 12:00PM
    @Scheduled(cron = "0 0 12 * * ?")
    public void updateExpired(){
        System.out.println("Running automatic scheduler");
        tradeService.updateExpired("Y", LocalDate.now());
    }
}
