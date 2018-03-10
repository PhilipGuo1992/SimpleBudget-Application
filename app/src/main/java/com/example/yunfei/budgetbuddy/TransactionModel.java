package com.example.yunfei.budgetbuddy;

import java.util.Date;

/**
 * Created by yunfei on 2018-03-06.
 */

public class TransactionModel {
    // transaction type
    private String budgetType;
    private String name;
    private double amount;
    private String transID;
    // use date
    private Date DATE;

    private String notes;

    public TransactionModel(){

    }

    public TransactionModel(String transID ,String budgetType, String name, double amount, Date DATE, String notes) {
        this.budgetType = budgetType;
        this.name = name;
        this.amount = amount;
        this.DATE = DATE;
        this.transID = transID;
        this.notes = notes;
    }

    public String getBudgetType() {
        return budgetType;
    }

    public void setBudgetType(String budgetType) {
        this.budgetType = budgetType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDATE() {
        return DATE;
    }

    public void setDATE(Date DATE) {
        this.DATE = DATE;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTransID() {
        return transID;
    }

    public void setTransID(String transID) {
        this.transID = transID;
    }
}
