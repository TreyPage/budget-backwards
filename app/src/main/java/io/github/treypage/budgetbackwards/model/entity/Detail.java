package io.github.treypage.budgetbackwards.model.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.io.Serializable;

@Entity(foreignKeys = {
        @ForeignKey(entity = Category.class, parentColumns = "id", childColumns = "id")
    })
public class Detail implements Serializable {

  private static final long serialVersionUID = 3L;

  @PrimaryKey()
  private long id;
  private String info;
  private double payout;
  private double percent;
  private String title;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public double getPayout() {
    return payout;
  }

  public void setPayout(double payout) {
    this.payout = payout;
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
