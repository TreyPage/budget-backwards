package io.github.treypage.budgetbackwards;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.splash_instructions);

    ImageButton next = findViewById(R.id.next_button);

    next.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
      }
    });
  }
}