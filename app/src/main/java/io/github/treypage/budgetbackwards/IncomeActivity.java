package io.github.treypage.budgetbackwards;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class IncomeActivity extends AppCompatActivity {

  private int userIncome;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_income);
    Toolbar toolbar = findViewById(R.id.toolbar2);
    setSupportActionBar(toolbar);
    ActionBar actionBar = getSupportActionBar();

    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
    Button submitIncomeButton = findViewById(R.id.submit_income);

    submitIncomeButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        final TextView userInput = findViewById(R.id.user_income);
        String userInputString = userInput.getText().toString();
        userIncome = Integer.parseInt(userInputString);

        Intent intent = new Intent(view.getContext(), ItemListActivity.class);
        intent.putExtra("userIncome", userIncome);
        startActivity(intent);
      }
    });
  }

  public int getUserIncome() {
    return userIncome;
  }
}

