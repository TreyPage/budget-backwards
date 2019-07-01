package io.github.treypage.budgetbackwards;

import android.app.Application;
import com.facebook.stetho.Stetho;

public class BudgetApp extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
  }
}
