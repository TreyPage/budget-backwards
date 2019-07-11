package io.github.treypage.budgetbackwards.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import io.github.treypage.budgetbackwards.R;
import io.github.treypage.budgetbackwards.model.entity.Category;
import io.github.treypage.budgetbackwards.model.entity.Category.Title;
import io.github.treypage.budgetbackwards.viewModel.MainViewModel;

public class CategoryFragment extends Fragment {

  public static CategoryFragment newInstance() {
    return new CategoryFragment();
  }

  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    setHasOptionsMenu(true);
    Bundle bundle = getArguments();
    int catId = bundle.getInt("category_id");
    Title thisCategory = Category.Title.values()[catId];
    final View view = inflater.inflate(R.layout.category_fragment, container, false);
    TextView catTitleView = view.findViewById(R.id.cat_frag_text);
    TextView catInfoView = view.findViewById(R.id.category_info);
    catTitleView.setText(thisCategory.toString());
    MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    viewModel.getCategory().observe(this, categories -> {
      catInfoView.setText(categories.get(catId).toString());
    });
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