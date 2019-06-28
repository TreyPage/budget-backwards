package io.github.treypage.budgetbackwards.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import io.github.treypage.budgetbackwards.R;
import io.github.treypage.budgetbackwards.model.entity.Category;
import io.github.treypage.budgetbackwards.viewModel.MainViewModel;
import java.util.ArrayList;
import java.util.List;
import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;


public class CategoryChartFragment extends Fragment {

  private MainViewModel mainViewModel;
  private PieChartView chart;

  public static CategoryChartFragment newInstance() {
    return new CategoryChartFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.category_chart, container, false);
    mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    mainViewModel.getCategory().observe(this, this::generateData);
    chart = rootView.findViewById(R.id.pie_chart);
    chart.setOnValueTouchListener(new ValueTouchListener());
    return rootView;
  }

  private void generateData(List<Category> categories) {
    List<SliceValue> values = new ArrayList<>();
    for (Category category : categories) {
      double percent = category.getPercent();
      SliceValue sliceValue = new SliceValue((float) percent,
          ChartUtils.nextColor());
      values.add(sliceValue);
    }

    PieChartData data = new PieChartData(values);
    data.setHasLabels(true);
    data.setHasLabelsOnlyForSelected(true);
    data.setHasLabelsOutside(true);
    data.setHasCenterCircle(true);

    data.setSlicesSpacing(10);
    data.setCenterText1("Pie Chart of");
    data.setCenterText1Typeface(Typeface.SANS_SERIF);
    data.setCenterText1FontSize(ChartUtils.px2sp(getResources().getDisplayMetrics().scaledDensity,
        (int) getResources().getDimension(R.dimen.pie_chart_text1_size)));

    data.setCenterText2("Categories");
    data.setCenterText2Typeface(Typeface.SANS_SERIF);

    data.setCenterText2FontSize(ChartUtils.px2sp(getResources().getDisplayMetrics().scaledDensity,
        (int) getResources().getDimension(R.dimen.pie_chart_text1_size)));

    chart.setPieChartData(data);

  }

  private class ValueTouchListener implements PieChartOnValueSelectListener {

    @Override
    public void onValueSelected(int arcIndex, SliceValue value) {
      //TODO On click should show title of category and amount of money/percentages necessary for category
      Toast toast = Toast.makeText(getActivity(), "Selected: " + value, Toast.LENGTH_SHORT);
      toast.setGravity(Gravity.CENTER, 0, 0);
      toast.show();
    }

    @Override
    public void onValueDeselected() {
      // Ignore
    }
  }
}


