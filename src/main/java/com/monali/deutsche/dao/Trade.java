package com.monali.deutsche.dao;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Trade Data access object
 *
 * @author  Monali Singh
 * @version 1.0
 * @since   2021-05-02
 */

@Entity
@Table
public class Trade implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private TradeIdNo tradeIdNo;
    private String counterPartyId;
    private String bookId;
    private LocalDate maturityDate;
    private LocalDate createdDate;
    private String expired;

    public Trade() {
    }

    public Trade(TradeIdNo tradeIdNo, String counterPartyId, String bookId, LocalDate maturityDate, LocalDate createdDate, String expired) {
        this.tradeIdNo = tradeIdNo;
        this.counterPartyId = counterPartyId;
        this.bookId = bookId;
        this.maturityDate = maturityDate;
        this.createdDate = createdDate;
        this.expired = expired;
    }

    public TradeIdNo getTradeIdNo() {
        return tradeIdNo;
    }

    public void setTradeIdNo(TradeIdNo tradeIdNo) {
        this.tradeIdNo = tradeIdNo;
    }

    public String getCounterPartyId() {
        return counterPartyId;
    }

    public void setCounterPartyId(String counterPartyId) {
        this.counterPartyId = counterPartyId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public LocalDate getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(LocalDate maturityDate) {
        this.maturityDate = maturityDate;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }
}
