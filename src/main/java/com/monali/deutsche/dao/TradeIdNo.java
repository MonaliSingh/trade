package com.monali.deutsche.dao;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Composite Primary Key class for Trade
 *
 * @author  Monali Singh
 * @version 1.0
 * @since   2021-05-02
 */

@Embeddable
public class TradeIdNo implements Serializable {

    private String tradeId;
    private Integer version;

    public TradeIdNo() {
    }

    public TradeIdNo(String tradeId, int version) {
        this.tradeId = tradeId;
        this.version = version;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        TradeIdNo tradeIdNo = (TradeIdNo) obj;
        return tradeId.equals(tradeIdNo.getTradeId()) &&
                version.equals(tradeIdNo.getVersion());
      //  return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tradeId,version);
    }
}
