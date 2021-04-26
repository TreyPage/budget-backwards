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

  /***
   * This entire Class' purpose is to show the the user information about a selected Category.
   * When created a bundle should be received from which ever method populated this fragment.
   * That bundle is extracted to get a category. This category is then used to populate all of the
   * fields with the information about that category.
   * @param inflater
   * @param container
   * @param savedInstanceState
   * @return
   */
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    setHasOptionsMenu(true);
    Bundle bundle = getArguments();
    String categoryName = bundle.getString("category_name");
    int catId = bundle.getInt("category_id");
    if (categoryName == null || categoryName.isEmpty()) {
      categoryName = Category.Title.values()[catId].abbreviation();
    }
    Title thisCategory = Category.Title.valueOf(categoryName.toUpperCase());
    return generateText(inflater, container, thisCategory);
  }

  private View generateText(LayoutInflater inflater, ViewGroup container, Title thisCategory) {
    final View view = inflater.inflate(R.layout.category_fragment, container, false);
    TextView catTitleView = view.findViewById(R.id.cat_frag_text);
    TextView catInfoView = view.findViewById(R.id.category_info);
    catTitleView.setText(thisCategory.toString());
    MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    viewModel.getCategory().observe(this,
        categories -> catInfoView.setText(categories.get(thisCategory.ordinal()).toString()));
    return view;
  }

}