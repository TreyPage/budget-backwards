package io.github.treypage.budgetbackwards.model.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

@Entity
public class Income {

  @PrimaryKey(autoGenerate = true)
  private long id;
  private double amount;
  private String date;


  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }
}
