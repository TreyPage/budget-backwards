package io.github.treypage.budgetbackwards.fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import io.github.treypage.budgetbackwards.R;
import io.github.treypage.budgetbackwards.model.entity.Expense;
import io.github.treypage.budgetbackwards.viewModel.CategoryViewModel;
import io.github.treypage.budgetbackwards.viewModel.ExpenseViewModel;

public class ExpenseFragment extends Fragment {

  private Context context;

  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
    this.context = context;
  }

  public static ExpenseFragment newInstance() {
    ExpenseFragment fragment = new ExpenseFragment();
    return fragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    final View view = inflater.inflate(R.layout.expense_fragment, container, false);

    final ExpenseViewModel viewModel = ViewModelProviders.of(this).get(ExpenseViewModel.class);

    viewModel.getExpenses().observe(this, expenses -> {
      final ArrayAdapter<Expense> adapter = new ArrayAdapter<>(context,
          android.R.layout.simple_list_item_1, expenses);

      ListView incomeListView = view.findViewById(R.id.expense_list);
      incomeListView.setAdapter(adapter);
    });

    Button newExpenseButton = view.findViewById(R.id.new_expense_button);
    final EditText newExpenseAmount = view.findViewById(R.id.new_expense_value);
    final EditText newExpenseName = view.findViewById(R.id.new_expense_name);
    newExpenseButton.setOnClickListener(v -> {
      Expense newExpense = new Expense();
      newExpense.setTitle(newExpenseName.getText().toString());
      newExpense.setAmount(Long.parseLong(newExpenseAmount.getText().toString()));
      viewModel.addExpense(newExpense);
      newExpenseAmount.setText("");
      newExpenseName.setText("");
    });

    final CategoryViewModel categoryViewModel = ViewModelProviders.of(getActivity())
        .get(CategoryViewModel.class);
    categoryViewModel.getCategory().observe(this, categories -> {
      final Spinner expenseSpinner = view.findViewById(R.id.category_spinner);
      SpinnerAdapter spinnerAdapter = new ArrayAdapter<>(context,
          android.R.layout.simple_spinner_item, categories);
      expenseSpinner.setAdapter(spinnerAdapter);
    });
//      Button addExpenseToCategoryButton = view.findViewById(R.id.add_expense_to_category_button);
//      addExpenseToCategoryButton.setOnClickListener(v -> categoryViewModel.getCategory().});
    return view;
  }
}