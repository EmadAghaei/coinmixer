package com.crypto.coinmixer.entity;



import javax.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name="TRANSACTION")
public class TransactionEntity extends BaseEntity{
    private String depositAddress;
    private String srcAddress;
    private BigDecimal amount;
    private String status;
    private Instant approvedDate;
    private BigDecimal deniedAmount;
    private BigDecimal fee;
    private Long approvedNumber;
    private BigDecimal verifiedAmount;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    private Set<TransactionDestinationEntity> transactionDestinationEntitySet;

    @ManyToOne
    @JoinColumn(name ="USER_ID" )
    private UserEntity userEntity;

    public Set<TransactionDestinationEntity> getTransactionDestinationEntitySet() {
        return transactionDestinationEntitySet;
    }

    public void setTransactionDestinationEntitySet(Set<TransactionDestinationEntity> transactionDestinationEntitySet) {
        this.transactionDestinationEntitySet = transactionDestinationEntitySet;
    }


    public String getSrcAddress() {
        return srcAddress;
    }

    public void setSrcAddress(String srcAddress) {
        this.srcAddress = srcAddress;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public String getDepositAddress() {
        return depositAddress;
    }

    public void setDepositAddress(String depositAddress) {
        this.depositAddress = depositAddress;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Instant approvedDate) {
        this.approvedDate = approvedDate;
    }

    public BigDecimal getDeniedAmount() {
        return deniedAmount;
    }

    public void setDeniedAmount(BigDecimal deniedAmount) {
        this.deniedAmount = deniedAmount;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public Long getApprovedNumber() {
        return approvedNumber;
    }

    public void setApprovedNumber(Long approvedNumber) {
        this.approvedNumber = approvedNumber;
    }

    public BigDecimal getVerifiedAmount() {
        return verifiedAmount;
    }

    public void setVerifiedAmount(BigDecimal verifiedAmount) {
        this.verifiedAmount = verifiedAmount;
    }
}
