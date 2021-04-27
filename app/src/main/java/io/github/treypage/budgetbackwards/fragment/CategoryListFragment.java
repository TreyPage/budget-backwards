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
  private MainViewModel viewModel;

  public static CategoryListFragment newInstance() {
    return new CategoryListFragment();
  }

  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
    this.context = context;
  }

  /***
   * Category list is a list of every single category available to the user. This list is populated
   * via the Enums in the Category entity. This list is clickable and when a user clicks on a
   * category, a new fragment replaces this one with all the information about that category.
   * @param inflater
   * @param container
   * @param savedInstanceState
   * @return
   */
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.category_list, container, false);
    viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    final ArrayAdapter<Title> adapter = new ArrayAdapter<>(context,
        android.R.layout.simple_list_item_1, Category.Title.values());
    generateListView(view, adapter);
    return view;
  }

  private void generateListView(View view,
      ArrayAdapter<Title> adapter) {
    final ListView categoryListView = view.findViewById(R.id.category_list);
    categoryListView.setAdapter(adapter);
    categoryListView.setOnItemClickListener(
        (arg0, arg1, position, arg3) -> viewModel.getCategory().observe(this, categories -> {
          final Bundle bundle = new Bundle();
          bundle.putInt("category_id", position);
          final Fragment fragment = CategoryFragment.newInstance();
          final FragmentTransaction transaction1 = Objects.requireNonNull(getActivity())
              .getSupportFragmentManager().beginTransaction();
          fragment.setArguments(bundle);
          transaction1.add(R.id.frame_layout, fragment);
          transaction1.hide(CategoryListFragment.this);
          transaction1.show(fragment);
          transaction1.commit();
        }));
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }
}