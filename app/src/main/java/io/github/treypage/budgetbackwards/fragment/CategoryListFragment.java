package io.github.treypage.budgetbackwards.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import io.github.treypage.budgetbackwards.R;
import io.github.treypage.budgetbackwards.model.entity.Category;
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

    final View view = inflater.inflate(R.layout.category_list, container, false);

    MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    viewModel.getCategory().observe(this, categories -> {
      final ArrayAdapter<Category> adapter = new ArrayAdapter<>(context,
          android.R.layout.simple_list_item_1, categories);

      ListView categoryListView = view.findViewById(R.id.category_list);
      categoryListView.setAdapter(adapter);
    });
    //TODO list displays percentages and $ amount
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


}