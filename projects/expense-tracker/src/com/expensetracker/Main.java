package com.expensetracker;

import com.expensetracker.model.Expense;
import com.expensetracker.service.ExpenseService;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ExpenseService service = new ExpenseService();

        while (true) {
            System.out.println("\n1. Add Expense\n2. View All\n3. Total\n4. Delete \n5. Exit");
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

                case 2 -> {
                        if (service.getAllExpenses().isEmpty()){
                            System.out.println("No Items Were added.");
                        }
                        else {
                            service.getAllExpenses().forEach(System.out::println);
                        }
                }
                case 3 -> System.out.println("Total: " + service.getTotal());
                case 4 -> {
                    if (service.getAllExpenses().isEmpty()){
                        System.out.println("No Expenses Were added.");
                    } else {
                        for (int i = 0; i < service.getAllExpenses().size(); i++) {
                            System.out.println((i+1)+ ". " + service.getAllExpenses().get(i));
                        }
                        boolean deleted = false;
                        while (!deleted){
                            System.out.print("Enter Expense number to delete: ");
                            int number = sc.nextInt();
                            int index = number - 1;
                            sc.nextLine();
                        try {
                            service.deleteExpense(index);
                            deleted = true;
                        } catch (Exception e) {
                                System.out.println("enter a valid number between 1 and less than " + (service.getAllExpenses().size() + 1));
                                sc.nextLine();
                        }
                    }
                        System.out.println("successfully deleted.");

                    }

                }
                case 5 ->  {
                    service.saveToFile();
                    System.exit(0);

                }
                default -> System.out.println("Invalid choice."); //comment
            }
        }
    }
}
