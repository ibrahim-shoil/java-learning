package com.expensetracker.model;

import java.time.LocalDate;

public class Expense {
    private double amount;
    private String category;
    private String description;
    private LocalDate date;
    private LocalDate lastEditedDate = null;

    public Expense(double amount, String category, String description, LocalDate date, LocalDate lastEditedDate) {
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.date = date;
        this.lastEditedDate = lastEditedDate;
    }
    public Expense(double amount, String category, String description, LocalDate date) {
        this(amount, category, description, date, null);
    }

    public double getAmount() { return amount; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public LocalDate getDate() { return date; }
    public LocalDate getLastEditedDate(){return lastEditedDate;}

    public void setAmount(double amount){
        this.amount = amount;
    }
    public void setCategory(String category){
        this.category = category;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public void setLastEditedDate(LocalDate lastEditedDate){
        this.lastEditedDate = lastEditedDate;
    }

    @Override
    public String toString() {
        if (lastEditedDate == null){
            return date + " | "  + category + " | " + amount + " | " + description ;
        }else {
            return date+ " | " + lastEditedDate + " | " + category + " | " + amount + " | " + description ;
        }
    }
}
