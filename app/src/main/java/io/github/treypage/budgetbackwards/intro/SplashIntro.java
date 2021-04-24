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
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import io.github.treypage.budgetbackwards.MainActivity;
import io.github.treypage.budgetbackwards.R;

public class SplashIntro extends AppCompatActivity {

  /**
   * onCreate will check if the user has ever seen this activity before. If they have they bypass
   * it. If they have not then the layout is populated with the IntroExpenseFragment and a
   * sharedPreference is edited to make it so the user will not see the activity again.
   *
   * @param savedInstanceState
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
    boolean previouslyStarted = prefs
        .getBoolean(getString(R.string.pref_previously_started), false);
    if (!previouslyStarted) {
      setContentView(R.layout.splash_intro);
      IntroExpenseFragment introExpenseFragment = new IntroExpenseFragment();
      FragmentManager manager = getSupportFragmentManager();
      FragmentTransaction transaction = manager.beginTransaction();
      transaction.add(R.id.intro_frame, introExpenseFragment).show(introExpenseFragment);
      transaction.commit();
    } else {
      Intent intent = new Intent(getBaseContext(), MainActivity.class);
      intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
      startActivity(intent);
    }
  }
//
//  public void finishedIntro() {
//    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
//    SharedPreferences.Editor edit = prefs.edit();
//    edit.putBoolean(getString(R.string.pref_previously_started), Boolean.TRUE);
//    edit.apply();
//  }
}
