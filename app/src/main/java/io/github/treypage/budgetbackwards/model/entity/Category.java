//    MIT License
//
//    Copyright (c) 2019 Terrell Page
//
//    Permission is hereby granted, free of charge, to any person obtaining a copy
//    of this software and associated documentation files (the "Software"), to deal
//    in the Software without restriction, including without limitation the rights
//    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//    copies of the Software, and to permit persons to whom the Software is
//    furnished to do so, subject to the following conditions:
//
//    The above copyright notice and this permission notice shall be included in all
//    copies or substantial portions of the Software.
//
//    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//    SOFTWARE.
package io.github.treypage.budgetbackwards.model.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

@Entity
public class Category {

  @PrimaryKey()
  private long id;
  private double percent;
  private String info;
  private double payout;
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

  /**
   * toString is being overridden here to provide the user the important information necessary for
   * the CategoryFragment.
   *
   * @return
   */
  @NonNull
  @Override
  public String toString() {
    String catPercent = String.format("%s%% of your income goes to ", Math.round(percent));
    String catPayout = String.format(". You should put $%.2f in this envelope.", payout);
    return String.format("%s%s%s", catPercent, name, catPayout);
  }

  /**
   * This Enum is every category available to the user.
   */
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