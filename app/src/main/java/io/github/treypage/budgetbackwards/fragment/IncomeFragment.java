package io.github.treypage.budgetbackwards.fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import io.github.treypage.budgetbackwards.R;
import io.github.treypage.budgetbackwards.model.entity.Income;
import io.github.treypage.budgetbackwards.model.service.QuotesService.NewQuote;
import io.github.treypage.budgetbackwards.viewModel.MainViewModel;
import java.util.Calendar;

public class IncomeFragment extends Fragment {


  public static IncomeFragment newInstance() {
    return new IncomeFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    Calendar rightNow = Calendar.getInstance();

    final View view = inflater.inflate(R.layout.income_fragment, container, false);

    Button listIncome = view.findViewById(R.id.list_all_income);
    listIncome.setOnClickListener(v -> {
      Fragment fragment = IncomeListFragment.newInstance();
      FragmentTransaction transaction1 = getActivity().getSupportFragmentManager().beginTransaction();
      transaction1.replace(R.id.frame_layout, fragment);
      transaction1.commit();
    });

    final MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    TextView swQuote = view.findViewById(R.id.sw_quote);
    Button newIncomeButton = view.findViewById(R.id.submit_income);
    new NewQuote()
        .setOnSuccessListener((quote) -> swQuote.setText(quote.getSwQuote()))
        .execute();
    final EditText newIncomeAmount = view.findViewById(R.id.user_income);
    final EditText newIncomeDate = view.findViewById(R.id.date_input);
    newIncomeDate.setText((rightNow.getTime().toString()));
    newIncomeButton.setOnClickListener(v -> {
      Income newIncome = new Income();
      newIncome.setDate((newIncomeDate.getText().toString()));
      try {
        newIncome.setAmount(Long.parseLong(newIncomeAmount.getText().toString()));
        viewModel.addIncome(newIncome);
        viewModel.incomeMath(Long.parseLong(newIncomeAmount.getText().toString()));
        newIncomeAmount.setText("");
      } catch (NumberFormatException noNumber) {
        Toast toast = Toast
            .makeText(getContext(), "Please input a valid amount.", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
      }
    });
    return view;
  }

}

