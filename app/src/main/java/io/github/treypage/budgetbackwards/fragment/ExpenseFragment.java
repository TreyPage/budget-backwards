package io.github.treypage.budgetbackwards.fragment;


import android.content.Context;
import android.content.Intent;
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
import com.google.android.material.snackbar.Snackbar;
import io.github.treypage.budgetbackwards.R;
import io.github.treypage.budgetbackwards.model.entity.Category;
import io.github.treypage.budgetbackwards.model.entity.Expense;
import io.github.treypage.budgetbackwards.splash.SplashIntro;
import io.github.treypage.budgetbackwards.viewModel.MainViewModel;

public class ExpenseFragment extends Fragment {

  private Context context;

  public static ExpenseFragment newInstance() {
    return new ExpenseFragment();
  }

  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
    this.context = context;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.expense_fragment, container, false);
    MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    generateListView(viewModel, view);
    Button newExpenseButton = view.findViewById(R.id.new_expense_button);
    final Spinner categorySpinner = view.findViewById(R.id.category_spinner);
    SpinnerAdapter spinnerAdapter = new ArrayAdapter<>(context,
        android.R.layout.simple_spinner_item, Category.Title.values());
    categorySpinner.setAdapter(spinnerAdapter);
    newExpenseButton.setOnClickListener(v -> submitExpense(viewModel, view, categorySpinner));
    return view;
  }

  private void submitExpense(MainViewModel viewModel, View view, Spinner categorySpinner) {
    final EditText newExpenseAmount = view.findViewById(R.id.new_expense_value);
    final EditText newExpenseName = view.findViewById(R.id.new_expense_name);
    Expense newExpense = new Expense();
    newExpense.setCategoryId(((Category.Title) categorySpinner.getSelectedItem()).ordinal());
    newExpense.setTitle(newExpenseName.getText().toString());
    try {
      newExpense.setAmount(Long.parseLong(newExpenseAmount.getText().toString()));
      viewModel.addExpense(newExpense);
      newExpenseAmount.setText("");
      newExpenseName.setText("");
    } catch (NumberFormatException noNumber) {
      Toast toast = Toast
          .makeText(getContext(), "Please input a valid amount.", Toast.LENGTH_SHORT);
      toast.setGravity(Gravity.CENTER, 0, 0);
      toast.show();
    }
  }

  private void generateListView(MainViewModel viewModel, View view) {
    ListView incomeListView = view.findViewById(R.id.expense_list);

    viewModel.getExpenses().observe(this, expenses -> {
      final ArrayAdapter<Expense> adapter = new ArrayAdapter<>(context,
          android.R.layout.simple_list_item_1, expenses);
      incomeListView.setOnItemClickListener((arg0, arg1, position, arg3) -> {
        Expense thisExpense = expenses.get(position);
        Snackbar snackbar = Snackbar.make(getView(), thisExpense.toString(), Snackbar.LENGTH_LONG);
        snackbar.setAction("Delete", v -> {
          viewModel.deleteExpense(thisExpense);
          checkExpense();
        });
        snackbar.show();
      });
      incomeListView.setAdapter(adapter);
    });
  }

  private void checkExpense() {
    MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    long expenses = 0;
    try {
      expenses = viewModel.getSumExpenses().getValue();
    } catch (Exception e) {
      //Do Nothing
    }
    if (expenses == 0) {
      startActivity(new Intent(getContext(), SplashIntro.class));
    }
  }

}