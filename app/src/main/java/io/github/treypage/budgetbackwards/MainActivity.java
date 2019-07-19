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
package io.github.treypage.budgetbackwards;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import io.github.treypage.budgetbackwards.fragment.CategoryChartFragment;
import io.github.treypage.budgetbackwards.fragment.CategoryListFragment;
import io.github.treypage.budgetbackwards.fragment.ExpenseFragment;
import io.github.treypage.budgetbackwards.fragment.IncomeFragment;
import io.github.treypage.budgetbackwards.fragment.Information;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    CategoryChartFragment categoryChartFragment = new CategoryChartFragment();
    CategoryListFragment categoryListFragment = new CategoryListFragment();
    IncomeFragment incomeFragment = new IncomeFragment();
    ExpenseFragment expenseFragment = new ExpenseFragment();
    Information information = new Information();
    getSupportFragmentManager().beginTransaction()
        .add(R.id.frame_layout, categoryChartFragment)
        .add(R.id.frame_layout, incomeFragment)
        .add(R.id.frame_layout, categoryListFragment)
        .add(R.id.frame_layout, expenseFragment)
        .add(R.id.frame_layout, information)
        .commitNow();
    showFragment(information);
    BottomNavigationView navigation = findViewById(R.id.navigation);
    navigation.setOnNavigationItemSelectedListener(item -> {
      switch (item.getItemId()) {
        case R.id.navigation_list:
          showFragment(categoryListFragment);
          break;
        case R.id.navigation_chart:
          showFragment(categoryChartFragment);
          break;
        case R.id.money_sign:
          showFragment(incomeFragment);
          break;
        case R.id.navigation_settings:
          showFragment(expenseFragment);
          break;
        case R.id.menu_info:
          showFragment(information);
          break;
      }
      return true;
    });
    navigation.setSelectedItemId(R.id.menu_info);
  }

  private void showFragment(Fragment fragment) {
    FragmentManager manager = getSupportFragmentManager();
    List<Fragment> fragments = manager.getFragments();
    FragmentTransaction transaction = manager.beginTransaction();
    for (Fragment frag : fragments) {
      if (frag == fragment) {
        transaction.show(frag);
      } else {
        transaction.hide(frag);
      }
    }
    transaction.commit();
  }
}
