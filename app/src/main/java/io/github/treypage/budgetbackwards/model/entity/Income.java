package io.github.treypage.budgetbackwards.model.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;

@Entity
public class Income implements Serializable {

  private static final long serialVersionUID = 2L;

  @PrimaryKey(autoGenerate = true)
  private long id;
  private long amount;

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
