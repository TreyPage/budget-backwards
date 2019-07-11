package io.github.treypage.budgetbackwards;

import android.app.Application;
import com.facebook.stetho.Stetho;
import io.github.treypage.budgetbackwards.model.database.BudgetDatabase;
import io.github.treypage.budgetbackwards.model.service.GoogleSignInService;

public class BudgetApp extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
    GoogleSignInService.setContext(this);
    new Thread(() -> BudgetDatabase.getInstance(this).getExpenseDao().delete()).start();
  }
}
