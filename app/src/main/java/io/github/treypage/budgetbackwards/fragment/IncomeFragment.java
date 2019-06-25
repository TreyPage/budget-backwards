package io.github.treypage.budgetbackwards.fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import io.github.treypage.budgetbackwards.R;
import io.github.treypage.budgetbackwards.model.entity.Income;
import io.github.treypage.budgetbackwards.viewModel.IncomeViewModel;

public class IncomeFragment extends Fragment {

  private Context context;

  public static IncomeFragment newInstance() {
    IncomeFragment fragment = new IncomeFragment();
    return fragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    final View view = inflater.inflate(R.layout.income_fragment, container, false);

    final IncomeViewModel viewModel = ViewModelProviders.of(this).get(IncomeViewModel.class);

    Button newIncomeButton = view.findViewById(R.id.submit_income);
    final EditText newIncomeAmount = view.findViewById(R.id.user_income);
    final EditText newIncomeDate = view.findViewById(R.id.date_input);
    newIncomeButton.setOnClickListener(v -> {
      Income newIncome = new Income();
      newIncome.setDate(Long.parseLong(newIncomeDate.getText().toString()));
      newIncome.setAmount(Long.parseLong(newIncomeAmount.getText().toString()));
      viewModel.addIncome(newIncome);
      newIncomeAmount.setText("");
      newIncomeDate.setText("");
    });
    return view;
  }

}

