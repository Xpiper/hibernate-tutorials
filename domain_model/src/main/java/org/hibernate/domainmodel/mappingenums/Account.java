package org.hibernate.domainmodel.mappingenums;

import javax.persistence.*;

@Entity
@Table(name = "Account")
public class Account {

    @Id
    @Column
    private Long id;

    @Column
    private String owner;

    @Convert(converter = MoneyConverter.class)
    private Money balance;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }
}
