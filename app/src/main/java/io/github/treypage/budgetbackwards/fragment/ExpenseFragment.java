package io.github.treypage.budgetbackwards.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import io.github.treypage.budgetbackwards.R;

public class ExpenseFragment extends Fragment {

  public static ExpenseFragment newInstance() {
    ExpenseFragment fragment = new ExpenseFragment();
    return fragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(R.layout.expense_fragment, container, false);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }
}