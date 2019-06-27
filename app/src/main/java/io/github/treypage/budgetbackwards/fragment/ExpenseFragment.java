package io.github.treypage.budgetbackwards.fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import io.github.treypage.budgetbackwards.R;
import io.github.treypage.budgetbackwards.model.entity.Category;
import io.github.treypage.budgetbackwards.model.entity.Expense;
import io.github.treypage.budgetbackwards.viewModel.ExpenseViewModel;

public class ExpenseFragment extends Fragment {

  private Context context;

  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
    this.context = context;
  }

  public static ExpenseFragment newInstance() {
    return new ExpenseFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    final View view = inflater.inflate(R.layout.expense_fragment, container, false);

    final ExpenseViewModel viewModel = ViewModelProviders.of(this).get(ExpenseViewModel.class);

    viewModel.getExpenses().observe(this, expenses -> {
      final ArrayAdapter<Expense> adapter = new ArrayAdapter<>(context,
          android.R.layout.simple_list_item_1, expenses);

//TODO user needs to be able to edit list

      ListView incomeListView = view.findViewById(R.id.expense_list);
      incomeListView.setAdapter(adapter);
    });

    final Spinner expenseSpinner = view.findViewById(R.id.category_spinner);
    SpinnerAdapter spinnerAdapter = new ArrayAdapter<>(context,
        android.R.layout.simple_spinner_item, Category.Title.values());

    expenseSpinner.setAdapter(spinnerAdapter);

    Button newExpenseButton = view.findViewById(R.id.new_expense_button);

    final EditText newExpenseAmount = view.findViewById(R.id.new_expense_value);

    final EditText newExpenseName = view.findViewById(R.id.new_expense_name);

    newExpenseButton.setOnClickListener(v -> {
      Expense newExpense = new Expense();
      newExpense.setCategoryId(((Category.Title)expenseSpinner.getSelectedItem()).ordinal());
      newExpense.setTitle(newExpenseName.getText().toString());
      try {
        newExpense.setAmount(Long.parseLong(newExpenseAmount.getText().toString()));
        viewModel.addExpense(newExpense);
        newExpenseAmount.setText("");
        newExpenseName.setText("");
      } catch (NumberFormatException noNumber){
        Toast toast = Toast.makeText(getContext(), "Please input a valid amount.", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
      }

    });
    return view;
  }
}