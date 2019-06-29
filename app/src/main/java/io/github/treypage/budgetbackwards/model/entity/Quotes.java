package io.github.treypage.budgetbackwards.model.entity;

import com.google.gson.annotations.SerializedName;

public class Quotes {

  @SerializedName("starWarsQuote")
  private String swQuote;

  public String getSwQuote() {
    return swQuote;
  }

  public void setSwQuote(String swQuote) {
    this.swQuote = swQuote;
  }

}
