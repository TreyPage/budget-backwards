package io.github.treypage.budgetbackwards.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Category {

  private static final long serialVersionUID = -7703982303849405697L;

  @PrimaryKey(autoGenerate = true)
  long id;
  double percent;
  String title;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public double getPercent() {
    return percent;
  }

  public void setPercent(double percent) {
    this.percent = percent;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

}
