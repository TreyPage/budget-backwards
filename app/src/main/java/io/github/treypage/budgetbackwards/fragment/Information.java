package io.github.treypage.budgetbackwards.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import io.github.treypage.budgetbackwards.R;
import io.github.treypage.budgetbackwards.model.service.GoogleSignInService;
import io.github.treypage.budgetbackwards.splash.Splash;


public class Information extends Fragment {

  private Context context;

  public static Information newInstance() {
    return new Information();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    setHasOptionsMenu(true);
    final View view = inflater.inflate(R.layout.instructions, container, false);
    view.findViewById(R.id.sign_out).setOnClickListener((button) -> signOut());
    return view;
  }

  private void signOut() {
    GoogleSignInService service = GoogleSignInService.getInstance();
    service.getClient().signOut().addOnCompleteListener((task) -> {
      service.setAccount(null);
      startActivity(new Intent(getContext().getApplicationContext(), Splash.class)
          .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    });

  }
}