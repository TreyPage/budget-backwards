package io.github.treypage.budgetbackwards.fragment;

import static android.widget.Toast.LENGTH_SHORT;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import io.github.treypage.budgetbackwards.MainActivity;
import io.github.treypage.budgetbackwards.R;
import io.github.treypage.budgetbackwards.model.entity.Category;
import io.github.treypage.budgetbackwards.model.entity.Category.Title;
import io.github.treypage.budgetbackwards.viewModel.MainViewModel;

public class CategoryListFragment extends Fragment {

  private Context context;

  public static CategoryListFragment newInstance() {
    return new CategoryListFragment();
  }

  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
    this.context = context;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    setHasOptionsMenu(true);
    final View view = inflater.inflate(R.layout.category_list, container, false);

    MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    viewModel.getCategory().observe(this, categories -> {
      final ArrayAdapter<Category> adapter = new ArrayAdapter<>(context,
          android.R.layout.simple_list_item_1, categories);

      ListView categoryListView = view.findViewById(R.id.category_list);
      categoryListView.setAdapter(adapter);


    });
//TODO user clicks on category and opens to details about category
    return view;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public String toString() {
    return super.toString();
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {

    Toast toast = Toast.makeText(getContext().getApplicationContext(),"", LENGTH_SHORT);
    toast.setGravity(Gravity.CENTER,0,0);
    toast.show();
    return super.onOptionsItemSelected(item);
  }
}