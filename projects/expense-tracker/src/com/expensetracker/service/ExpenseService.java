package com.expensetracker.service;

import com.expensetracker.model.Category;
import com.expensetracker.model.Expense;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExpenseService {
    private List<Expense> expenses = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();

    public ExpenseService() {
        categories.add(new Category("Food"));
        categories.add(new Category("Transport"));
        categories.add(new Category("Shopping"));
        categories.add(new Category("Bills"));
        categories.add(new Category("Entertainment"));
        loadFromFile();
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

    public void saveToFile(){
        try (PrintWriter writer = new PrintWriter("expenses.csv")){
            for (Expense expense: expenses){
                String line = expense.getAmount() + ";" +
                        expense.getCategory() + "\"" +
                        expense.getDescription() + "\"" + ";" +
                        expense.getDate();
                writer.println(line);
            }
            System.out.println("csv file created successfully...");


        } catch (IOException e)
        {
            System.err.println("Error writing to CSV file: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void loadFromFile() {
            try {
                Scanner scanner = new Scanner(new File("expenses.csv"));
                while (scanner.hasNextLine()){
                    String line = scanner.nextLine();
                    String[] parts = line.split(";");
                    double amount = Double.parseDouble(parts[0]);
                    String category = parts[1];
                    String description = parts[2];
                    LocalDate date = LocalDate.parse(parts[3]);
                    Expense e = new Expense(amount, category, description, date);
                    expenses.add(e);


                }
                scanner.close();
            } catch (Exception e) {
                System.err.println("Error loading: " + e.getMessage());
                e.printStackTrace();
            }
    }



}
