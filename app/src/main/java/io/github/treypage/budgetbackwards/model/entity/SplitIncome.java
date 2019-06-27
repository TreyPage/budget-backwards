package io.github.treypage.budgetbackwards.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
    @ForeignKey(entity = Income.class, parentColumns = "id", childColumns = "income_id"),
    @ForeignKey(entity = Category.class, parentColumns = "id", childColumns = "category_id")
})
public class SplitIncome {

  @PrimaryKey(autoGenerate = true)
  private long id;
  @ColumnInfo(name = "income_id", index = true)
  private long incomeId;
  @ColumnInfo(name = "category_id", index = true)
  private long categoryId;
  private long income;
  private long expense;


  public long getIncome() {
    return income;
  }

  public void setIncome(long income) {
    this.income = income;
  }

  public long getExpense() {
    return expense;
  }

  public void setExpense(long expense) {
    this.expense = expense;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getIncomeId() {
    return incomeId;
  }

  public void setIncomeId(long incomeId) {
    this.incomeId = incomeId;
  }

  public long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(long categoryId) {
    this.categoryId = categoryId;
  }

  public static class BudgetMath {

    public static long whatPercent(long incomeAmount, long expenseAmount) {
      if (incomeAmount == 0){
        return 0;
      }
        double percent = expenseAmount / incomeAmount;
        return (long) percent;
    }

    public static long budgetLeftovers(long incomeAmount, long expenseAmount) {
      return incomeAmount - expenseAmount;
    }

  }
}
