package io.github.treypage.budgetbackwards.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import java.io.Serializable;

@Entity(foreignKeys = {
        @ForeignKey(entity = Income.class, parentColumns = "id", childColumns = "income_id"),
        @ForeignKey(entity = Category.class, parentColumns = "id", childColumns = "category_id")
    })
public class SplitIncome implements Serializable {

  private static final long serialVersionUID = 1032617491635158879L;

  @PrimaryKey(autoGenerate = true)
  private long id;
  @ColumnInfo(name = "income_id", index = true)
  private long incomeId;
  @ColumnInfo(name = "category_id", index = true)
  private long categoryId;


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


}
