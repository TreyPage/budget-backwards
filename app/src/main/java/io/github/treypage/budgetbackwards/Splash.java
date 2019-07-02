package io.github.treypage.budgetbackwards;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.splash_instructions);

    Thread background = new Thread() {
      public void run() {
        try {
          // Thread will sleep for 10 seconds
          sleep(10*1000);
          // After 10 seconds redirect to another intent
          Intent intent = new Intent(getBaseContext(), MainActivity.class);
          startActivity(intent);
          //Switch activity
          finish();
        } catch (Exception e) {
          // Do nothing?? maybe
        }
      }
    };
    // start thread
    background.start();
  }
}
