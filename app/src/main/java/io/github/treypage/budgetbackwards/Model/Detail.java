package io.github.treypage.budgetbackwards.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Detail {

  private static final long serialVersionUID = 6079857357244107338L;

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
