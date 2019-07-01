package io.github.treypage.budgetbackwards.model.entity;

import com.google.gson.annotations.SerializedName;

public class Quote {

  @SerializedName("starWarsQuote")
  private String swQuote;

  public void SwQuote(String swQuote) {
    this.swQuote = swQuote;
  }

  public String getSwQuote() {
    return String.format("%s", swQuote);
  }

}
