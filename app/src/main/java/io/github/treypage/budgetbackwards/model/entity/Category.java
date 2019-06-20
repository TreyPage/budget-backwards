package io.github.treypage.budgetbackwards.model.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.io.Serializable;

@Entity
public class Category implements Serializable {

  private static final long serialVersionUID = 4L;

  @PrimaryKey(autoGenerate = true)
  private long id;
  private double percent;
  private String title;

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
