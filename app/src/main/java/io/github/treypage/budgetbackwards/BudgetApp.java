package io.github.treypage.budgetbackwards;

import android.app.Application;
import com.facebook.stetho.Stetho;
import io.github.treypage.budgetbackwards.model.database.BudgetDatabase;
import io.github.treypage.budgetbackwards.model.entity.Category;

public class BudgetApp extends Application {
  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);

    new Thread(() -> {
      Category category = new Category();
      category.setTitle("Housing");
      BudgetDatabase.getInstance(this).getCategoryDao().insert(category);
    }).start();
  }
}
