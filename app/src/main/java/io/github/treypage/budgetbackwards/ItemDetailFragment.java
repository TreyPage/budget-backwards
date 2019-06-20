package io.github.treypage.budgetbackwards;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import io.github.treypage.budgetbackwards.budget.BudgetContent;
import io.github.treypage.budgetbackwards.budget.BudgetContent.BudgetItem;

public class ItemDetailFragment extends Fragment {

  public static final String ARG_ITEM_ID = "item_id";

  private BudgetItem mItem;

  public ItemDetailFragment() {
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (getArguments().containsKey(ARG_ITEM_ID)) {
      mItem = BudgetContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

      Activity activity = this.getActivity();
      CollapsingToolbarLayout appBarLayout = activity.findViewById(R.id.toolbar_layout);
      if (appBarLayout != null) {
        appBarLayout.setTitle(mItem.content);
      }
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.item_detail, container, false);
    if (mItem != null) {
      ((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.details);
    }
    return rootView;
  }
}
