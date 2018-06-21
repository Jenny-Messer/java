// Copyright (c) 2018 Travelex Ltd

package atm.entity;

import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class AccountEntity {

    @Id
    @Column
    private UUID accountId;

    @Column
    private BigDecimal balance;

    @Column
    private String currency;

    @Column
    private UUID customerId;

    public AccountEntity() {
    }

    public AccountEntity(BigDecimal balance, String currency, UUID accountId) {
        this.balance = balance;
        this.currency = currency;
        this.accountId = accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getCurrency() {
        return currency;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }
}
