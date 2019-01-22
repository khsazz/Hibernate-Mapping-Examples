package com.hibernate.examples.mapping_association.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sazzad on 21-Apr-16 [5:12 PM].
 */


@Entity
public class Account {
    private int accountId;
    private String accountName;
    private String location;
//    todo: Uncomment in bidirectional association
//    private List<User> userList = new ArrayList<User>();
    private List<Transaction> transactionList = new ArrayList<Transaction>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accountId", nullable = false)
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Basic
    @Column(name = "accountName", nullable = true, length = 45)
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @Basic
    @Column(name = "location", nullable = true, length = 45)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

//    todo: Uncomment in bidirectional association
//    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "accountList")
//    public List<User> getUserList() {
//        return userList;
//    }

//    public void setUserList(List<User> userList) {
//        this.userList = userList;
//    }

//    todo : uncomment if Transaction and Account has an unidirectional OneToMany association from Account->Transaction
//    @OneToMany(cascade = CascadeType.ALL)
//    todo : uncomment if Transaction and Account has an bi-directional association
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
//    @JoinColumn(name = "account_id", nullable = false)
    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (accountId != account.accountId) return false;
        if (accountName != null ? !accountName.equals(account.accountName) : account.accountName != null) return false;
        if (location != null ? !location.equals(account.location) : account.location != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = accountId;
        result = 31 * result + (accountName != null ? accountName.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }
}
