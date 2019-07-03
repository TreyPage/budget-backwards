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

    setContentView(R.layout.splash_intro);

    Button submit = findViewById(R.id.splash_submit);
    EditText rentText = findViewById(R.id.rent_box);
    MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

    submit.setOnClickListener(view -> {

      try {
        Expense newRent = new Expense();
        newRent.setCategoryId(0);
        newRent.setTitle("Rent/Mortgage");
        newRent.setAmount(Long.parseLong(rentText.getText().toString()));
        viewModel.addExpense(newRent);

        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
      } catch (NumberFormatException noNumber) {
        Toast toast = Toast
            .makeText(getApplication(), "Please input a valid amount.", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
      }

    });

  }

}
