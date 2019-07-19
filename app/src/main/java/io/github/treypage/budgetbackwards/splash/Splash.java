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
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import io.github.treypage.budgetbackwards.R;
import io.github.treypage.budgetbackwards.model.database.BudgetDatabase;
import io.github.treypage.budgetbackwards.model.service.GoogleSignInService;

public class Splash extends AppCompatActivity {

  private static final int LOGIN_REQUEST_CODE = 1001;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.splash_instructions);
    new Thread(() -> BudgetDatabase.getInstance(this).getExpenseDao().delete()).start();
    findViewById(R.id.sign_in).setOnClickListener(
        (view) -> signIn());
    findViewById(R.id.next_button).setOnClickListener(
        (view) -> switchToNext());
  }


  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == LOGIN_REQUEST_CODE) {
      try {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        GoogleSignInAccount account = task.getResult(ApiException.class);
        GoogleSignInService.getInstance().setAccount(account);
        switchToNext();
      } catch (ApiException e) {
        Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show();
      }
    }
  }

  private void switchToNext() {
    Intent intent = new Intent(this, SplashIntro.class);
    startActivity(intent);
  }

  private void signIn() {
    Intent intent = GoogleSignInService.getInstance().getClient().getSignInIntent();
    startActivityForResult(intent, LOGIN_REQUEST_CODE);
  }

  @Override
  protected void onStart() {
    super.onStart();
    GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
    if (account != null) {
      GoogleSignInService.getInstance().setAccount(account);
      switchToNext();
    }
  }

}
