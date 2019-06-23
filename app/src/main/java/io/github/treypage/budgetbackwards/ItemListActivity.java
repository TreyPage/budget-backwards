package io.github.treypage.budgetbackwards;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import io.github.treypage.budgetbackwards.viewModel.CategoryViewModel;
import io.github.treypage.budgetbackwards.viewModel.CategoryViewModel.CategoryItem;
import io.github.treypage.budgetbackwards.fragment.IncomeFragment;
import java.util.List;

/**
 * An activity representing a list of Items. This activity has different presentations for handset
 * and tablet-size devices. On handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing item details. On tablets, the activity presents
 * the list of items and item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends AppCompatActivity {

  /**
   * Whether or not the activity is in two-pane mode, i.e. running on a tablet device.
   */
  private boolean mTwoPane;
  private int userIncome;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_item_list);
    Toolbar toolbar = findViewById(R.id.toolbar);
    userIncome = getIntent().getIntExtra("userIncome", 0);
    setSupportActionBar(toolbar);
    toolbar.setTitle(getTitle());

    FloatingActionButton fab = findViewById(R.id.money_sign);
    final Intent intent = new Intent(this, IncomeFragment.class);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        startActivity(intent);
      }
    });

    if (findViewById(R.id.item_detail_container) != null) {
      // The detail container view will be present only in the
      // large-screen layouts (res/values-w900dp).
      // If this view is present, then the
      // activity should be in two-pane mode.
      mTwoPane = true;
    }

    View recyclerView = findViewById(R.id.item_list);
    assert recyclerView != null;
    setupRecyclerView((RecyclerView) recyclerView);
  }

  private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
    recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, CategoryViewModel.ITEMS, mTwoPane));
  }

  public class SimpleItemRecyclerViewAdapter
      extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

    private final ItemListActivity mParentActivity;
    private final List<CategoryItem> mValues;
    private final boolean mTwoPane;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        CategoryItem item = (CategoryItem) view.getTag();
        if (mTwoPane) {
          Bundle arguments = new Bundle();
          arguments.putString(io.github.treypage.budgetbackwards.fragment.CategoryFragment.ARG_ITEM_ID, item.id);
          io.github.treypage.budgetbackwards.fragment.CategoryFragment fragment = new io.github.treypage.budgetbackwards.fragment.CategoryFragment();
          fragment.setArguments(arguments);
          mParentActivity.getSupportFragmentManager().beginTransaction()
              .replace(R.id.item_detail_container, fragment)
              .commit();
        } else {

          Context context = view.getContext();
          Intent intent = new Intent(context, ItemDetailActivity.class);
          intent.putExtra("userIncome", userIncome);
          intent.putExtra(io.github.treypage.budgetbackwards.fragment.CategoryFragment.ARG_ITEM_ID, item.id);

          context.startActivity(intent);
        }
      }
    };

    SimpleItemRecyclerViewAdapter(ItemListActivity parent,
        List<CategoryItem> items,
        boolean twoPane) {
      mValues = items;
      mParentActivity = parent;
      mTwoPane = twoPane;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.item_list_content, parent, false);
      return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
      holder.mIdView.setText(mValues.get(position).id);
      holder.mContentView.setText(mValues.get(position).content);

      holder.itemView.setTag(mValues.get(position));
      holder.itemView.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
      return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

      final TextView mIdView;
      final TextView mContentView;

      ViewHolder(View view) {
        super(view);
        mIdView = view.findViewById(R.id.id_text);
        mContentView = view.findViewById(R.id.content);
      }
    }
  }
}
