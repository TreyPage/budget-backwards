package io.github.treypage.budgetbackwards.model.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Income {

  @PrimaryKey(autoGenerate = true)
  private long id;
  private long amount;
  private long date;

  public long getDate() {
    return date;
  }

  public void setDate(long date) {
    this.date = date;
  }

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
