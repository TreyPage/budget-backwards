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
package io.github.treypage.budgetbackwards.intro;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import com.google.android.material.snackbar.Snackbar;
import io.github.treypage.budgetbackwards.R;
import io.github.treypage.budgetbackwards.model.entity.Category;
import io.github.treypage.budgetbackwards.model.entity.Expense;
import io.github.treypage.budgetbackwards.viewModel.MainViewModel;

public class IntroExpenseFragment extends Fragment {

    private Context context;

    public static IntroExpenseFragment newInstance() {
        return new IntroExpenseFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    /***
     * IntroExpenseFragment lives inside of the frame layout of the SplashIntro. It is almost
     * identical to the expense fragment that is available to the user inside the full app, with two
     * major differences. Every time a user inputs an expense a snackbar will ask the user if they are
     * done adding expenses, if they select yes, the user will be sent to the IntroIncomeFragment.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.intro_expense_fragment, container, false);
        final Spinner categorySpinner = view.findViewById(R.id.category_spinner);
        final EditText newExpenseAmount = view.findViewById(R.id.new_expense_value);
        final EditText newExpenseName = view.findViewById(R.id.new_expense_name);

        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        generateListView(viewModel, view);
        Button newExpenseButton = view.findViewById(R.id.new_expense_button);
        SpinnerAdapter spinnerAdapter = new ArrayAdapter<>(context,
                android.R.layout.simple_spinner_item, Category.Title.values());
        categorySpinner.setAdapter(spinnerAdapter);
        newExpenseButton.setOnClickListener(newView -> {
            if (newExpenseAmount.getText().toString().equals("") || newExpenseName.getText().toString().equals("")) {
                nextSnackbar(view);
            } else {
                newExpenseButton.setOnClickListener(v -> {
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
                });
            }
        });
        return view;
    }

    private void nextSnackbar(View view) {
        Snackbar snackbar = Snackbar.make(view, "There was a blank field are you done with Expenses?", Snackbar.LENGTH_LONG);
        snackbar.setAction("Yes", v -> {
            IntroIncomeFragment introIncomeFragment = new IntroIncomeFragment();
            FragmentManager manager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.intro_frame, introIncomeFragment).commit();
        });
        snackbar.show();
    }

    private void generateListView(MainViewModel viewModel, View view) {
        ListView incomeListView = view.findViewById(R.id.expense_list);

        viewModel.getExpenses().observe(this, expenses -> {
            final ArrayAdapter<Expense> adapter = new ArrayAdapter<>(context,
                    android.R.layout.simple_list_item_1, expenses);
            incomeListView.setOnItemClickListener((arg0, arg1, position, arg3) -> {
                Expense thisExpense = expenses.get(position);
                Snackbar snackbar = Snackbar.make(getView(), thisExpense.toString(), Snackbar.LENGTH_LONG);
                snackbar.setAction("Delete", v -> viewModel.deleteExpense(thisExpense));
                snackbar.show();
            });
            incomeListView.setAdapter(adapter);
        });
    }

}