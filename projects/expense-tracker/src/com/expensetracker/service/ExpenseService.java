package com.expensetracker.service;

import com.expensetracker.model.Category;
import com.expensetracker.model.Expense;
import java.util.ArrayList;
import java.util.List;

public class ExpenseService {
    private List<Expense> expenses = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();

    public ExpenseService() {
        categories.add(new Category("Food"));
        categories.add(new Category("Transport"));
        categories.add(new Category("Shopping"));
        categories.add(new Category("Bills"));
        categories.add(new Category("Entertainment"));
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenses;
    }

    public double getTotal() {
        return expenses.stream().mapToDouble(Expense::getAmount).sum();
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void addCategory(String newCategory) {
        categories.add(new Category(newCategory));
    }
}
