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
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import io.github.treypage.budgetbackwards.R;
import io.github.treypage.budgetbackwards.model.entity.Category;
import io.github.treypage.budgetbackwards.model.entity.Category.Title;
import io.github.treypage.budgetbackwards.viewModel.MainViewModel;
import java.util.Objects;

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

    final ArrayAdapter<Title> adapter = new ArrayAdapter<>(context,
        android.R.layout.simple_list_item_1, Category.Title.values());

    ListView categoryListView = view.findViewById(R.id.category_list);
    categoryListView.setDividerHeight(20);
    categoryListView.setClickable(true);
    categoryListView.setOnItemClickListener((arg0, arg1, position, arg3) -> {
      MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
      viewModel.getCategory().observe(this, categories -> {

        Bundle bundle = new Bundle();
        bundle.putInt("category_id", position);
        Fragment fragment = CategoryFragment.newInstance();
        FragmentTransaction transaction1 = Objects.requireNonNull(getActivity())
            .getSupportFragmentManager().beginTransaction();
        fragment.setArguments(bundle);
        transaction1.replace(R.id.frame_layout, fragment);
        transaction1.commit();

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