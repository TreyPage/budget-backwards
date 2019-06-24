package io.github.treypage.budgetbackwards.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import io.github.treypage.budgetbackwards.R;

public class CategoryChartFragment extends Fragment {

  public static CategoryChartFragment newInstance() {
    CategoryChartFragment fragment = new CategoryChartFragment();
    return fragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(R.layout.category_chart, container, false);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }
}

