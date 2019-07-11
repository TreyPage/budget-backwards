package io.github.treypage.budgetbackwards.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import io.github.treypage.budgetbackwards.R;
import io.github.treypage.budgetbackwards.model.entity.Income;
import io.github.treypage.budgetbackwards.viewModel.MainViewModel;

public class IncomeListFragment extends Fragment {

  private Context context;

  public static IncomeListFragment newInstance() {
    return new IncomeListFragment();
  }

  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
    this.context = context;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    setHasOptionsMenu(true);
    final View view = inflater.inflate(R.layout.income_list, container, false);
    MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    viewModel.getIncome().observe(this, incomes -> {
      ArrayAdapter<Income> adapter = new ArrayAdapter<>(context,
          android.R.layout.simple_list_item_1, incomes);
      ListView incomeListView = view.findViewById(R.id.income_list);
      incomeListView.setDividerHeight(20);
      incomeListView.setAdapter(adapter);
    });
    return view;

  }
}
