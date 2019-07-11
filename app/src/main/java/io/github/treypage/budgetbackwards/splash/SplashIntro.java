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
    viewModel.getSumExpenses().observe(this, shazam -> {
      long expenses = 0;
      try {
        expenses = shazam;
      } catch (Exception e) {
        //Do Nothing
      }
      if (expenses == 0) {
        setContentView(R.layout.splash_intro);
        submitExpense();
      } else {
        startActivity(new Intent(getBaseContext(), MainActivity.class));
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
