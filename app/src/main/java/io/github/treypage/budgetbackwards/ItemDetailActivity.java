package io.github.treypage.budgetbackwards;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class ItemDetailActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_item_detail);
    Toolbar toolbar = findViewById(R.id.detail_toolbar);
    setSupportActionBar(toolbar);
    final int userIncome = getIntent().getIntExtra("userIncome", 0);

    FloatingActionButton fab = findViewById(R.id.money_sign);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Your income is: " + userIncome, Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
      }
    });
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
    if (savedInstanceState == null) {
      Bundle arguments = new Bundle();
      arguments.putString(ItemDetailFragment.ARG_ITEM_ID,
          getIntent().getStringExtra(ItemDetailFragment.ARG_ITEM_ID));
      ItemDetailFragment fragment = new ItemDetailFragment();
      fragment.setArguments(arguments);
      getSupportFragmentManager().beginTransaction()
          .add(R.id.item_detail_container, fragment)
          .commit();
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == android.R.id.home) {
      navigateUpTo(new Intent(this, ItemListActivity.class));
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
