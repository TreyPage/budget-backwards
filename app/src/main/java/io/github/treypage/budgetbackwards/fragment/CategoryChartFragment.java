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
import io.github.treypage.budgetbackwards.viewModel.MainViewModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;


public class CategoryChartFragment extends Fragment {

  private PieChartView chart;

  public static CategoryChartFragment newInstance() {
    return new CategoryChartFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.category_chart, container, false);
    MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
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
      sliceValue.setLabel(category.getName());
      values.add(sliceValue);
    }

    PieChartData data = new PieChartData(values);
    data.setHasLabels(true);
    data.setHasLabelsOnlyForSelected(false);
    data.setHasLabelsOutside(false);
    data.setHasCenterCircle(true);
    data.setSlicesSpacing(10);

    Typeface newFont = ResourcesCompat.getFont(getContext(), R.font.cutive);
    data.setCenterText1("Pie Chart of");
    data.setCenterText2("Categories");

    data.setCenterText1Typeface(newFont);
    data.setCenterText2Typeface(newFont);

    data.setCenterText1FontSize(ChartUtils.px2sp(getResources().getDisplayMetrics().scaledDensity,
        (int) getResources().getDimension(R.dimen.pie_chart_text1_size)));
    data.setCenterText2FontSize(ChartUtils.px2sp(getResources().getDisplayMetrics().scaledDensity,
        (int) getResources().getDimension(R.dimen.pie_chart_text1_size)));

    chart.setPieChartData(data);

  }

  private class ValueTouchListener implements PieChartOnValueSelectListener {

    @Override
    public void onValueSelected(int arcIndex, SliceValue value) {
      MainViewModel viewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity()))
          .get(MainViewModel.class);
      viewModel.getCategory().observe(getActivity(), categories -> {
        Bundle bundle = new Bundle();
        bundle.putInt("category_id", arcIndex);
        try {
          Fragment fragment = CategoryFragment.newInstance();
          FragmentTransaction transaction1 = getActivity()
              .getSupportFragmentManager().beginTransaction();
          fragment.setArguments(bundle);
          transaction1.replace(R.id.frame_layout, fragment);
          transaction1.commit();
        } catch (NullPointerException w) {
          //DO NOTHING
        }

      });
    }

    @Override
    public void onValueDeselected() {
      // Ignore
    }
  }
}