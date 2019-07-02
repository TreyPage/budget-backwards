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
import com.google.android.material.snackbar.Snackbar;
import io.github.treypage.budgetbackwards.R;
import io.github.treypage.budgetbackwards.model.entity.Category.Title;
import io.github.treypage.budgetbackwards.viewModel.MainViewModel;

public class CategoryFragment extends Fragment {

  private Context context;

  public static CategoryFragment newInstance() {
    return new CategoryFragment();
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
    final View view = inflater.inflate(R.layout.category_fragment, container, false);

    final ArrayAdapter<Title> adapter = new ArrayAdapter<>(context,
        android.R.layout.simple_list_item_1, io.github.treypage.budgetbackwards.model.entity.Category.Title.values());

    ListView categoryListView = view.findViewById(R.id.category_list);
    categoryListView.setDividerHeight(20);
    categoryListView.setClickable(true);
    categoryListView.setOnItemClickListener((arg0, arg1, position, arg3) -> {
      MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
      viewModel.getCategory().observe(this, categories -> {
        io.github.treypage.budgetbackwards.model.entity.Category thisCategory = categories.get(position);
        Snackbar snackbar = Snackbar
            .make(getView(), thisCategory.toString(), Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("DISMISS", v -> snackbar.dismiss());
        snackbar.show();
      });
    });
    categoryListView.setAdapter(adapter);
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