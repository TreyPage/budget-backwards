package io.github.treypage.budgetbackwards.model.entity;

import org.apache.commons.lang3.StringUtils;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.room.util.StringUtil;

@Entity
public class Category {

  @PrimaryKey(autoGenerate = false)
  private long id;
  private double percent;
  private String info;
  private long payout;
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public long getPayout() {
    return payout;
  }

  public void setPayout(long payout) {
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

  @NonNull
  @Override
  public String toString() {
    String catPercent = String.format(" is %s%% of your income.", Math.round(percent));
    String catPayout = String.format("You should put $%s in this envelope.", payout);

    return String.format("%s%s%s", name, catPercent, catPayout);
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
    @NonNull
    @Override
    public String toString() {
      return abbreviation();
    }

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