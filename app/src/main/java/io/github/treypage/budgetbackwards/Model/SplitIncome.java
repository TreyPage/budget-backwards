package io.github.treypage.budgetbackwards.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.io.Serializable;

@Entity(primaryKeys = {"id", "id"},
    indices = {@Index("id"), @Index("id")},
    foreignKeys = {
        @ForeignKey(entity = Income.class, parentColumns = "id", childColumns = "incomeId"),
        @ForeignKey(entity = Category.class, parentColumns = "id", childColumns = "categoryId")
    })
public class SplitIncome implements Serializable {

  private static final long serialVersionUID = 1032617491635158879L;

  @PrimaryKey(autoGenerate = true)
  long id;
  @ColumnInfo(name = "income_id", index = true)
  long incomeId;
  @ColumnInfo(name = "category_id", index = true)
  long categoryId;


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
