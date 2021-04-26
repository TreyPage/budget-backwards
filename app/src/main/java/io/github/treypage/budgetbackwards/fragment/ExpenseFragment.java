//    MIT License
//
//    Copyright (c) 2019 Terrell Page
//
//    Permission is hereby granted, free of charge, to any person obtaining a copy
//    of this software and associated documentation files (the "Software"), to deal
//    in the Software without restriction, including without limitation the rights
//    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//    copies of the Software, and to permit persons to whom the Software is
//    furnished to do so, subject to the following conditions:
//
//    The above copyright notice and this permission notice shall be included in all
//    copies or substantial portions of the Software.
//
//    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//    SOFTWARE.
package io.github.treypage.budgetbackwards.fragment;

import android.content.Context;
import android.os.Build.VERSION_CODES;
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
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import com.google.android.material.snackbar.Snackbar;
import io.github.treypage.budgetbackwards.R;
import io.github.treypage.budgetbackwards.model.entity.Category;
import io.github.treypage.budgetbackwards.model.entity.Expense;
import io.github.treypage.budgetbackwards.viewModel.MainViewModel;
import java.util.Comparator;
import java.util.List;

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

  /***
   * ExpenseFragment lives inside of the frame layout of the MainActivity. This fragment provides a
   * list of all expenses the user has put in and listens to touch by the user on those expenses, if
   * a user touches an expense it will ask the user if they would like to delete it. Below this list
   * is an expense name, expense amount, and a spinner filled with all available categories.
   * Once the user clicks submit these three pieces of information are attached to the expense and
   * added to the list as well as the database.
   */
  @RequiresApi(api = VERSION_CODES.N)
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
    categorySpinner.setGravity(View.TEXT_ALIGNMENT_CENTER);
    categorySpinner.setAdapter(spinnerAdapter);
    newExpenseButton.setOnClickListener(v -> submitExpense(viewModel, view, categorySpinner));
    return view;
  }

  private void submitExpense(MainViewModel viewModel, View view, Spinner categorySpinner) {
    final EditText newExpenseAmount = view.findViewById(R.id.new_expense_value);
    final EditText newExpenseName = view.findViewById(R.id.new_expense_name);
    Expense newExpense = new Expense();
    newExpense.setCategoryId(((Category.Title) categorySpinner.getSelectedItem()).ordinal());
    try {
      newExpense.setTitle(newExpenseName.getText().toString());
    } catch (StringIndexOutOfBoundsException titleOutOfBounds) {
      Toast toast = Toast
          .makeText(getContext(), "Please give it a title.", Toast.LENGTH_SHORT);
      toast.setGravity(Gravity.CENTER, 0, 0);
      toast.show();
    }
    try {
      newExpense.setAmount(Long.parseLong(newExpenseAmount.getText().toString()));
      viewModel.addExpense(newExpense);
      newExpenseAmount.setText("");
      newExpenseName.setText("");
      viewModel.getOneIncome().observe(this, viewModel::incomeMath);
      Fragment fragment = ExpenseFragment.newInstance();
      FragmentTransaction transaction1 = requireActivity()
          .getSupportFragmentManager().beginTransaction();
      List<Fragment> allFragments = requireActivity()
          .getSupportFragmentManager().getFragments();
      for (Fragment oneFragment : allFragments) {
        transaction1.hide(oneFragment);
      }
      transaction1.add(R.id.frame_layout, fragment);
      transaction1.show(fragment);
      transaction1.commit();

    } catch (NumberFormatException noNumber) {
      Toast toast = Toast
          .makeText(getContext(), "Please input a valid amount.", Toast.LENGTH_SHORT);
      toast.setGravity(Gravity.CENTER, 0, 0);
      toast.show();
    }
  }

  @RequiresApi(api = VERSION_CODES.N)
  private void generateListView(MainViewModel viewModel, View view) {
    viewModel.getExpenses().observe(this, expenses -> {
      final ArrayAdapter<Expense> adapter = new ArrayAdapter<>(context,
          android.R.layout.simple_list_item_1, expenses);
      ListView incomeListView = view.findViewById(R.id.expense_list);
      adapter.sort(Comparator.comparing(Expense::getCategoryId));
      incomeListView.setAdapter(adapter);
      incomeListView.setOnItemClickListener((arg0, arg1, position, arg3) -> {
        Expense thisExpense = expenses.get(position);
        if (adapter.getCount() > 1) {
          Snackbar snackbar = Snackbar
              .make(requireView(), thisExpense.toString(),
                  Snackbar.LENGTH_LONG);
          snackbar.setAction("Delete", v -> viewModel.deleteExpense(thisExpense));
          snackbar.show();
        } else {
          Toast toast = Toast
              .makeText(getContext(), "At least 1 Expense is required.\nDividing by 0 hurts.",
                  Toast.LENGTH_SHORT);
          toast.setGravity(Gravity.CENTER, 0, 0);
          toast.show();
        }
      });
      incomeListView.setAdapter(adapter);
    });
  }
}