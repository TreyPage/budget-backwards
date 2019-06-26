package io.github.treypage.budgetbackwards;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import io.github.treypage.budgetbackwards.fragment.CategoryChartFragment;
import io.github.treypage.budgetbackwards.fragment.CategoryListFragment;
import io.github.treypage.budgetbackwards.fragment.ExpenseFragment;
import io.github.treypage.budgetbackwards.fragment.IncomeFragment;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Fragment fragment = CategoryListFragment.newInstance();
    FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
    transaction1.replace(R.id.frame_layout, fragment);
    transaction1.commit();
    BottomNavigationView navigation = findViewById(R.id.navigation);
    navigation.setOnNavigationItemSelectedListener(item -> {
      Fragment selectedFragment = null;
      switch (item.getItemId()) {
        case R.id.navigation_list:
          selectedFragment = CategoryListFragment.newInstance();
          break;
        case R.id.navigation_chart:
          selectedFragment = CategoryChartFragment.newInstance();
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
