package io.github.treypage.budgetbackwards.model.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import java.io.Serializable;

@Entity
public class Category implements Serializable {

  private static final long serialVersionUID = 4L;

  @PrimaryKey(autoGenerate = true)
  private long id;
  private double percent;
  @TypeConverters(TitleConverter.class)
  private Title title;
  private String info;

  public Title getTitle() {
    return title;
  }

  public void setTitle(Title title) {
    this.title = title;
  }

  private double payout;

  public enum Title {
    HOUSING, PHONE, UTILITIES, FOOD, TRANSPORTATION, OTHER, SAVINGS, GROCERIES, DEBT, INSURANCE, CHILDCARE, PETS, TUITION
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

  public static class TitleConverter {

    @TypeConverter
    public static Title stringToTitle(String value) {
      return Title.valueOf(value);
    }

    @TypeConverter
    public static String titleToString(Title title) {
      return title.name();
    }

  }
}