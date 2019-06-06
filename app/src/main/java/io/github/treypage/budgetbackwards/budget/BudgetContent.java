package io.github.treypage.budgetbackwards.budget;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BudgetContent {

  public static final List<BudgetItem> ITEMS = new ArrayList<BudgetItem>();

  public static final Map<String, BudgetItem> ITEM_MAP = new HashMap<>();

  static {
    addItem(createBudgetItem(1, "Housing",
        "Housing is typically a large percentage of a person's expenses. Avoid exceeding "
            + "35% of your income while including all necessary fees related to housing such as "
            + "HOA, Insurance, and upkeep."));
    addItem(createBudgetItem(2, "Groceries",
        "Groceries/ Food should be less than 15% of your income."));
    addItem(createBudgetItem(3, "Phone",
        "Phone, Utilities, and TV should not exceed 10% of your income."));
    addItem(createBudgetItem(4, "Utilities",
        "Phone, Utilities, and TV should not exceed 10% of your income."));
    addItem(createBudgetItem(5, "TV",
        "Phone, Utilities, and TV should not exceed 10% of your income."));
    addItem(createBudgetItem(6, "Childcare",
        "An estimated amount of childcare according to Care.com is $196 per child for a "
            + "child care center."));
    addItem(createBudgetItem(7, "Tuition",
        "Pay off your student loan debt at the same rate you pay off any other debt. Commit"
            + "about 10% of your income to it. If you are currently attending school and have a set "
            + "amount you are required to pay, put that amount here."));
    addItem(createBudgetItem(8, "Pets",
        "This obviously depends a lot on the type and amount of pets but most estimates are"
            + "around $30 a month per pet."));
    addItem(createBudgetItem(9, "Transportation",
        "Transportation expenses include car payments, car insurance, gas and car "
            + "maintenance. These expenses should be limited to 15 percent of your income."));
    addItem(createBudgetItem(10, "Personal Care",
        "Personal care can be considered personal hygiene, clothes, and "
            + "anything else that is essential; depending on the reason for the purchase and the "
            + "context in which it is purchased. Attempt to stay under 5% of your income."));
    addItem(createBudgetItem(11, "Health Insurance",
        "The percentage of your income devoted to health insurance can vary drastically "
            + "depending on size of family and current state of health, please seek out a "
            + "professional in the health insurance field to make sure you have the best plan."));
    addItem(createBudgetItem(12, "Credit Cards",
        "Try to put 10% of your income into paying debt."));
    addItem(createBudgetItem(13, "Loans",
        "Try to put 10% of your income into paying debt."));
    addItem(createBudgetItem(14, "Savings",
        "Aim for 10% every your income."));
    addItem(createBudgetItem(15, "Other",
        "The other section can be used for anything you would like and excess money will "
            + "be put into this category after everything else has been covered. If you would like "
            + "to add something not included in this list please add it here and a value in this "
            + "field will calculate."));
  }

  private static void addItem(BudgetItem item) {
    ITEMS.add(item);
    ITEM_MAP.put(item.id, item);
  }

  private static BudgetItem createBudgetItem(int position, String content, String details) {
    return new BudgetItem(String.valueOf(position), content, details);
  }

  public static class BudgetItem {

    public final String id;
    public final String content;
    public final String details;

    public BudgetItem(String id, String content, String details) {
      this.id = id;
      this.content = content;
      this.details = details;
    }

    @NonNull
    @Override
    public String toString() {
      return content;
    }
  }
}