package com.expensetracker;

import com.expensetracker.model.Expense;
import com.expensetracker.service.ExpenseService;
import java.time.LocalDate;
import java.util.Scanner;//test

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ExpenseService service = new ExpenseService();

        while (true) {
            System.out.println("\n1. Add Expense\n2. View All\n3. Total\n4. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Amount: ");
                    double amount = sc.nextDouble();
                    sc.nextLine();

                    System.out.println("\nChoose a category:");
                    var categories = service.getCategories();
                    for (int i = 0; i < categories.size(); i++) {
                        System.out.println((i + 1) + ". " + categories.get(i).getName());
                    }
                    System.out.println((categories.size() + 1) + ". Other");

                    System.out.print("Enter your choice: ");
                    int catChoice = sc.nextInt();
                    sc.nextLine();

                    String category;
                    if (catChoice == categories.size() + 1) {
                        System.out.print("Enter new category name: ");
                        category = sc.nextLine();
                        service.addCategory(category);
                    } else {
                        category = categories.get(catChoice - 1).getName();
                    }

                    System.out.print("Description: ");
                    String desc = sc.nextLine();

                    service.addExpense(new Expense(amount, category, desc, LocalDate.now()));
                    System.out.println("Expense added under '" + category + "'.");
                }

                case 2 -> service.getAllExpenses().forEach(System.out::println);
                case 3 -> System.out.println("Total: " + service.getTotal());
                case 4 -> System.exit(0);
                default -> System.out.println("Invalid choice."); //comment
            }
        }
    }
}
