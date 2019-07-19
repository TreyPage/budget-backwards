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
package io.github.treypage.budgetbackwards.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import io.github.treypage.budgetbackwards.MainActivity;
import io.github.treypage.budgetbackwards.R;
import io.github.treypage.budgetbackwards.model.entity.Expense;
import io.github.treypage.budgetbackwards.viewModel.MainViewModel;

public class SplashIntro extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    checkExpense();
  }

  private void submitExpense() {
    Button submit = findViewById(R.id.splash_submit);
    submit.setOnClickListener(view -> {
      try {
        newExpense();
      } catch (NumberFormatException noNumber) {
        notANumber();
      }
    });
  }

  private void checkExpense() {
    MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    viewModel.getSumExpenses().observe(this, sumExpenses -> {
      long expenses = 0;
      try {
        expenses = sumExpenses;
      } catch (Exception e) {
        //Do Nothing
      }
      if (expenses == 0) {
        setContentView(R.layout.splash_intro);
        submitExpense();
      } else {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
      }
    });
  }


  private void notANumber() {
    Toast toast = Toast
        .makeText(getApplication(), getString(R.string.invalid_number), Toast.LENGTH_SHORT);
    toast.setGravity(Gravity.CENTER, 0, 0);
    toast.show();
  }

  private void newExpense() {
    EditText rentText = findViewById(R.id.rent_box);
    MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    Expense newSavings = new Expense();
    newSavings.setCategoryId(6);
    newSavings.setTitle(getString(R.string.savings));
    newSavings.setAmount(Long.parseLong(rentText.getText().toString()));
    viewModel.addExpense(newSavings);
    Intent intent = new Intent(getBaseContext(), MainActivity.class)
        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
  }
}
