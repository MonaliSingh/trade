package com.monali.deutsche.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Repository
public interface TradeRepository extends JpaRepository<Trade,TradeIdNo> {

    //Returns maximum version for the given trade id
    @Query("select Max(trade.tradeIdNo.version) from Trade trade where trade.tradeIdNo.tradeId = ?1")
    Integer findMaxVersion(String tid);

    //Updates the Expired flag
    @Transactional
    @Modifying
    @Query("Update Trade trade set trade.expired=?1 where trade.maturityDate<?2")
    void updateExpired(String expired, LocalDate currentDate);
}
