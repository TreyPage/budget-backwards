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

import android.content.Intent;
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
import io.github.treypage.budgetbackwards.MainActivity;
import io.github.treypage.budgetbackwards.R;
import io.github.treypage.budgetbackwards.fragment.IncomeListFragment;
import io.github.treypage.budgetbackwards.model.entity.Income;
import io.github.treypage.budgetbackwards.model.service.QuotesService.NewQuote;
import io.github.treypage.budgetbackwards.viewModel.MainViewModel;
import java.util.Calendar;

public class IntroIncomeFragment extends Fragment {


  public static IntroIncomeFragment newInstance() {
    return new IntroIncomeFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.intro_income_fragment, container, false);

    TextView swQuote = view.findViewById(R.id.sw_quote);
    new NewQuote()
        .setOnSuccessListener((quote) -> swQuote.setText(quote.getSwQuote()))
        .execute();
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
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
      } catch (NumberFormatException noNumber) {
        Toast toast = Toast
            .makeText(getContext(), "Please input a valid amount.", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
      }
    });
  }

}

