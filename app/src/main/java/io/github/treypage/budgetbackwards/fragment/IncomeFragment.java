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

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import io.github.treypage.budgetbackwards.R;
import io.github.treypage.budgetbackwards.model.entity.Income;
import io.github.treypage.budgetbackwards.viewModel.MainViewModel;
import java.util.Calendar;

public class IncomeFragment extends Fragment {

  public static IncomeFragment newInstance() {
    return new IncomeFragment();
  }

  /***
   * This fragment has a random Star Wars quote showing at the top that is refreshed every time
   * the user opens this fragment. This is done by the NewQuote class via an asyncTask. There are
   * two text fields available to a user to input a number for income and a date for that income.
   * The date field is pre-populated with date and time. The user can either submit that income or
   * view a list of all income.
   * @param inflater
   * @param container
   * @param savedInstanceState
   * @return
   */
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.income_fragment, container, false);

    listedIncome(view);
    submitNewIncome(view);
    return view;
  }

  private void submitNewIncome(View view) {
    final MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    final EditText newIncomeAmount = view.findViewById(R.id.user_income);
    final EditText newIncomeDate = view.findViewById(R.id.date_input);
    Button newIncomeButton = view.findViewById(R.id.submit_income);
    Calendar rightNow = Calendar.getInstance();
    newIncomeDate.setText((rightNow.getTime().toString()));
    newIncomeButton.setOnClickListener(v -> {
      Income newIncome = new Income();
      newIncome.setDate((newIncomeDate.getText().toString()));
      try {
        newIncome.setAmount(Long.parseLong(newIncomeAmount.getText().toString()));
        viewModel.addIncome(newIncome);
        viewModel.incomeMath(Long.parseLong(newIncomeAmount.getText().toString()));
        newIncomeAmount.setText("");
        Toast toast = Toast
            .makeText(getContext(), "Input created. \n\n Happy Budgeting!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        Fragment fragment = IncomeFragment.newInstance();
        FragmentTransaction transaction1 = getActivity()
            .getSupportFragmentManager().beginTransaction();
        transaction1.add(R.id.frame_layout, fragment);
        transaction1.hide(this);
        transaction1.show(fragment);
        transaction1.commit();

      } catch (NumberFormatException noNumber) {
        Toast toast = Toast
            .makeText(getContext(), "Please input a valid amount.", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
      }
    });
  }

  private void listedIncome(View view) {
    Button listIncome = view.findViewById(R.id.list_all_income);
    listIncome.setOnClickListener(v -> {
      Fragment fragment = IncomeListFragment.newInstance();
      FragmentTransaction transaction1 = getActivity().getSupportFragmentManager()
          .beginTransaction();
      transaction1.add(R.id.frame_layout, fragment);
      transaction1.show(fragment);
      transaction1.hide(IncomeFragment.this);
      transaction1.commitNow();
    });
  }

}

