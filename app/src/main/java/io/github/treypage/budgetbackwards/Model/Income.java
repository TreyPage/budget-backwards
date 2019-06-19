package io.github.treypage.budgetbackwards.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Income {

  private static final long serialVersionUID = 3620476713574910644L;

  @PrimaryKey(autoGenerate = true)
  long id;
  long amount;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getAmount() {
    return amount;
  }

  public void setAmount(long amount) {
    this.amount = amount;
  }
}
