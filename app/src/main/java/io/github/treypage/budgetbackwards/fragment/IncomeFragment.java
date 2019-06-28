package io.github.treypage.budgetbackwards.fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import io.github.treypage.budgetbackwards.R;
import io.github.treypage.budgetbackwards.model.entity.Income;
import io.github.treypage.budgetbackwards.viewModel.MainViewModel;

public class IncomeFragment extends Fragment {

  //TODO display random quote API at top of fragment

  public static IncomeFragment newInstance() {
    IncomeFragment fragment = new IncomeFragment();
    return fragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    final View view = inflater.inflate(R.layout.income_fragment, container, false);

    final MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

    Button newIncomeButton = view.findViewById(R.id.submit_income);
    final EditText newIncomeAmount = view.findViewById(R.id.user_income);
    final EditText newIncomeDate = view.findViewById(R.id.date_input);
    newIncomeButton.setOnClickListener(v -> {
      Income newIncome = new Income();
      newIncome.setDate((newIncomeDate.getText().toString()));
      try {
        newIncome.setAmount(Long.parseLong(newIncomeAmount.getText().toString()));
        viewModel.addIncome(newIncome);

        //TODO do math on income amount

        newIncomeAmount.setText("");
        newIncomeDate.setText("");
      } catch (NumberFormatException noNumber){
        Toast toast = Toast.makeText(getContext(), "Please input a valid amount.", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
      }
    });
    return view;
  }

}

