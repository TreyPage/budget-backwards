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

package io.github.treypage.budgetbackwards.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import io.github.treypage.budgetbackwards.R;
import io.github.treypage.budgetbackwards.model.entity.Category;
import io.github.treypage.budgetbackwards.model.entity.Category.Title;
import io.github.treypage.budgetbackwards.model.entity.Expense;
import io.github.treypage.budgetbackwards.viewModel.MainViewModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;

public class CategoryChartFragment extends Fragment {

  private PieChartView chart;
  private MainViewModel viewModel;

  /***
   * This method simply creates an instance of the CategoryChartFragment.
   * @return
   */
  public static CategoryChartFragment newInstance() {
    return new CategoryChartFragment();
  }

  /***
   * onCreateView is doing all of the heavy lifting here. It sets the layout to the category_chart
   * which sits inside the frame layout of the main activity. It is observing changes to incomeMath
   * in the MainViewModel for percentages. These percentages are out of 100% for each Category.
   * This information is used to populate the Pie Chart via generateData. It is then listing to touch
   * by the user on the pie chart.
   * @param inflater
   * @param container
   * @param savedInstanceState
   * @return
   */
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.category_chart, container, false);
    viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    viewModel.getOneIncome().observe(this, viewModel::incomeMath);
    viewModel.getCategory().observe(this, this::generateData);
    chart = rootView.findViewById(R.id.pie_chart);
    chart.setOnValueTouchListener(new ValueTouchListener());
    return rootView;
  }

  private void generateData(List<Category> categories) {
    funMoney(categories);
    List<SliceValue> values = new ArrayList<>();
    for (Category category : categories) {
      double percent = category.getPercent();
      SliceValue sliceValue = new SliceValue((float) percent,
          ChartUtils.nextColor());
      if (0 == percent) {
        sliceValue.setLabel("");
      } else {
        sliceValue.setLabel(category.getName());
      }
      values.add(sliceValue);
    }

    PieChartData data = new PieChartData(values);
    data.setHasLabels(true);
    data.setHasLabelsOnlyForSelected(false);
    data.setHasLabelsOutside(false);
    data.setHasCenterCircle(true);
    data.setSlicesSpacing(10);

    Typeface newFont = ResourcesCompat.getFont(Objects.requireNonNull(getContext()), R.font.cutive);
    data.setCenterText1("Pie Chart of");
    data.setCenterText2("Categories");

    data.setCenterText1Typeface(newFont);
    data.setCenterText2Typeface(newFont);

    data.setCenterText1FontSize(
        ChartUtils.px2sp(getResources().getDisplayMetrics().scaledDensity,
            (int) getResources().getDimension(R.dimen.pie_chart_text1_size)));
    data.setCenterText2FontSize(
        ChartUtils.px2sp(getResources().getDisplayMetrics().scaledDensity,
            (int) getResources().getDimension(R.dimen.pie_chart_text1_size)));

    chart.setPieChartData(data);
  }

  private void funMoney(List<Category> allCategories) {
    for (Category oneCategory : allCategories) {
      if (oneCategory.getName().equals(Title.EXTRA.abbreviation())) {
        continue;
      }
      viewModel.getExpenses(oneCategory.getId()).observe(this, allExpenses -> {
        double total = 0;
        for (Expense oneExpense : allExpenses) {
          total += oneExpense.getAmount();
        }
        if (oneCategory.getPayout() > total) {
          Category category = new Category();
          category.setName(Title.EXTRA.toString());
          category.setId(Title.EXTRA.ordinal());
          category.setPayout(oneCategory.getPayout() - total);
          oneCategory.setPayout(total);
          viewModel.updateCategory(Arrays.asList(category, oneCategory));
        }
      });
    }
  }

  private class ValueTouchListener implements PieChartOnValueSelectListener {

    /***
     * When a slice of the pie chart is selected onValueSelected is called with the arcIndex and
     * value to determine the category that was selected. Based on which slice was selected a new
     * fragment will replace the user's view with a snapshot of that selected Category information.
     * @param arcIndex
     * @param value
     */
    @Override
    public void onValueSelected(int arcIndex, SliceValue value) {
      String category = String.valueOf(value.getLabelAsChars());
      viewModel.getCategory().observe(Objects.requireNonNull(getActivity()), categories -> {
        Bundle bundle = new Bundle();
        bundle.putString("category_name", category);
        try {
          Fragment fragment = CategoryFragment.newInstance();
          FragmentTransaction transaction1 = getActivity()
              .getSupportFragmentManager().beginTransaction();
          fragment.setArguments(bundle);
          transaction1.add(R.id.frame_layout, fragment);
          transaction1.hide(CategoryChartFragment.this);
          transaction1.show(fragment);
          transaction1.commit();
        } catch (NullPointerException w) {
          //DO NOTHING
        }

      });
    }

    /***
     * onValueDeselected is ignored here because it is impossible for a user to accomplish. When
     * a slice is selected the fragment is replaced never allowing a user to deselect.
     */
    @Override
    public void onValueDeselected() {
      // Ignore
    }
  }

}