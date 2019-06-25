package io.github.treypage.budgetbackwards.model.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

@Entity
public class Category {

  @PrimaryKey(autoGenerate = true)
  private long id;
  private double percent;
  private String info;
//  private String title;
  private double payout;
  @TypeConverters(TitleConverter.class)
  public enum Title {
    HOUSING, PHONE, UTILITIES, FOOD, TRANSPORTATION, OTHER, SAVINGS, GROCERIES, DEBT, INSURANCE, CHILDCARE, PETS, TUITION
  }

//
//  public String getTitle() {
//    return title;
//  }
//
//  public void setTitle(String title) {
//    this.title = title;
//  }


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

//  private static final String[] ABBREVIATIONS = {
//      "HOUSING", "PHONE", "UTILITIES", "FOOD", "TRANSPORTATION", "OTHER", "SAVINGS", "GROCERIES", "DEBT", "INSURANCE", "CHILDCARE", "PETS", "TUITION"
//  };
//
//  public String abbreviation() {
//    return ABBREVIATIONS[ordinal()];
//  }
//
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