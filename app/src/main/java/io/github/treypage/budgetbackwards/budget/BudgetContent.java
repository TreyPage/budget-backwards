package io.github.treypage.budgetbackwards.budget;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by Android template
 * wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class BudgetContent {

  /**
   * An array of sample (dummy) items.
   */
  public static final List<BudgetItem> ITEMS = new ArrayList<BudgetItem>();

  /**
   * A map of sample (dummy) items, by ID.
   */
  public static final Map<String, BudgetItem> ITEM_MAP = new HashMap<>();

  static {
    addItem(createBudgetItem(1, "Housing"));
    addItem(createBudgetItem(2, "Groceries"));
    addItem(createBudgetItem(3, "Phone"));
    addItem(createBudgetItem(4, "Utilities"));
    addItem(createBudgetItem(5, "TV"));
    addItem(createBudgetItem(6, "Childcare"));
    addItem(createBudgetItem(7, "Tuition"));
    addItem(createBudgetItem(8, "Pets"));
    addItem(createBudgetItem(9, "Transportation"));
    addItem(createBudgetItem(10, "Personal Care"));
    addItem(createBudgetItem(11, "Insurance"));
    addItem(createBudgetItem(12, "Credit Cards"));
    addItem(createBudgetItem(13, "Loans"));
    addItem(createBudgetItem(14, "Savings"));
    addItem(createBudgetItem(15, "Other"));
  }

  private static void addItem(BudgetItem item) {
    ITEMS.add(item);
    ITEM_MAP.put(item.id, item);
  }

  private static BudgetItem createBudgetItem(int position, String content) {
    return new BudgetItem(String.valueOf(position), content, makeDetails(position, content));
  }

  private static String makeDetails(int position, String content) {
    StringBuilder builder = new StringBuilder();
    builder.append("Details about: ").append(content);
    for (int i = 0; i < position; i++) {
      builder.append("\nMore details information here.");
    }
    return builder.toString();
  }

  /**
   * A dummy item representing a piece of content.
   */
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