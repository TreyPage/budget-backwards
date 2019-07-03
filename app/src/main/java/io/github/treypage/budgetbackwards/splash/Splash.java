package io.github.treypage.budgetbackwards.splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import io.github.treypage.budgetbackwards.MainActivity;
import io.github.treypage.budgetbackwards.R;

public class Splash extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
    boolean previouslyStarted = prefs
        .getBoolean(getString(R.string.pref_previously_started), false);
    if (!previouslyStarted) {
      SharedPreferences.Editor edit = prefs.edit();
      edit.putBoolean(getString(R.string.pref_previously_started), Boolean.TRUE);
      edit.commit();
      setContentView(R.layout.splash_instructions);
      ImageButton next = findViewById(R.id.next_button);
      next.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Intent intent = new Intent(getBaseContext(), SplashIntro.class);
          startActivity(intent);
        }
      });
    } else {
      Intent intent = new Intent(getBaseContext(), MainActivity.class);
      startActivity(intent);
    }
  }
}
