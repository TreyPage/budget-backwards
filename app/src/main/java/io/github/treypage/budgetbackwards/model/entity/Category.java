package io.github.treypage.budgetbackwards.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

@Entity
public class Category {

  @PrimaryKey(autoGenerate = false)
  private long id;
  private double percent;
  private String info;
  private double payout;
  @ColumnInfo(name = "category_string", index = true)
  private String categoryString;

  public String getCategoryString() {
    return categoryString;
  }

  public void setCategoryString(String categoryString) {
    this.categoryString = categoryString;
  }

  @TypeConverters(TitleConverter.class)
  public enum Title {
    HOUSING, PHONE, UTILITIES, FOOD, TRANSPORTATION, OTHER, SAVINGS, GROCERIES, DEBT, INSURANCE, CHILDCARE, PETS, TUITION;

    public String abbreviation() {
      switch (this) {
        case HOUSING:
          return "Housing";
        case PHONE:
          return "Phone";
        case UTILITIES:
          return "Utilities";
        case FOOD:
          return "Food";
        case TRANSPORTATION:
          return "Transportation";
        case OTHER:
          return "Other";
        case PETS:
          return "Pets";
        case CHILDCARE:
          return "Childcare";
        case INSURANCE:
          return "Insurance";
        case DEBT:
          return "Debt";
        case GROCERIES:
          return "Groceries";
        case SAVINGS:
          return "Savings";
        case TUITION:
          return "Tuition";
      }
      return null;
    }

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
      return title.abbreviation();
    }

  }

}