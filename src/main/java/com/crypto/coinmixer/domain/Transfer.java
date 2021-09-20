package com.crypto.coinmixer.domain;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Transfer {
    private String srcAddress;
    private String dstAddress;
    private BigDecimal amount;
    private String userId;
    private BigDecimal fee;

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getSrcAddress() {
        return srcAddress;
    }

    public void setSrcAddress(String srcAddress) {
        this.srcAddress = srcAddress;
    }

    public String getDstAddress() {
        return dstAddress;
    }

    public void setDstAddress(String dstAddress) {
        this.dstAddress = dstAddress;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transfer)) return false;

        Transfer transfer = (Transfer) o;

        if (srcAddress != null ? !srcAddress.equals(transfer.srcAddress) : transfer.srcAddress != null) return false;
        if (dstAddress != null ? !dstAddress.equals(transfer.dstAddress) : transfer.dstAddress != null) return false;
        if (amount != null ? !amount.equals(transfer.amount) : transfer.amount != null) return false;
        return userId != null ? userId.equals(transfer.userId) : transfer.userId == null;
    }

    @Override
    public int hashCode() {
        int result = srcAddress != null ? srcAddress.hashCode() : 0;
        result = 31 * result + (dstAddress != null ? dstAddress.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }


}
