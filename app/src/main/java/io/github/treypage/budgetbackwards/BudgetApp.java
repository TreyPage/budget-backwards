package io.github.treypage.budgetbackwards;

import android.app.Application;
import com.facebook.stetho.Stetho;
import com.squareup.picasso.Picasso;

public class BudgetApp extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
    Picasso.setSingletonInstance(new Picasso.Builder(this).build());
  }
}
