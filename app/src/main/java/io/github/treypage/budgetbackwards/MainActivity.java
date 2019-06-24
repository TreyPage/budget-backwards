package io.github.treypage.budgetbackwards;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;
import edu.cnm.deepdive.atthemovies.fragment.ExpenseFragment;
import io.github.treypage.budgetbackwards.fragment.CategoryFragment;
import io.github.treypage.budgetbackwards.fragment.IncomeFragment;


public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    BottomNavigationView navigation = findViewById(R.id.navigation);
    navigation.setOnNavigationItemSelectedListener(item -> {
      Fragment selectedFragment = null;
      switch (item.getItemId()) {
        case R.id.navigation_list:
          selectedFragment = CategoryFragment.newInstance();
          break;
        case R.id.navigation_chart:
          selectedFragment = CategoryFragment.newInstance();
          break;
        case R.id.money_sign:
          selectedFragment = IncomeFragment.newInstance();
          break;
        case R.id.navigation_settings:
          selectedFragment = ExpenseFragment.newInstance();
          break;
      }
      FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
      assert selectedFragment != null;
      transaction.replace(R.id.frame_layout, selectedFragment);
      transaction.commit();
      return true;
    });
  }
}
