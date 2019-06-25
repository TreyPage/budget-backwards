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
import io.github.treypage.budgetbackwards.viewModel.CategoryViewModel;

public class CategoryListFragment extends Fragment {

  private Context context;

  public static CategoryListFragment newInstance() {
    CategoryListFragment fragment = new CategoryListFragment();
    return fragment;
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

    final CategoryViewModel viewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);

    viewModel.getCategory().observe(this, categories -> {
      final ArrayAdapter<Category> adapter = new ArrayAdapter<>(context,
          android.R.layout.simple_list_item_1, categories);

      ListView categoryListView = view.findViewById(R.id.category_list);
      categoryListView.setAdapter(adapter);
    });
//
//      Button newExpenseButton = view.findViewById(R.id.new_expense_button);
//      final EditText newExpenseAmount = view.findViewById(R.id.new_expense_value);
//      final EditText newExpenseName = view.findViewById(R.id.new_expense_name);
//      newExpenseButton.setOnClickListener(v -> {
//        Category newExpense = new Category();
//        newExpense.setTitle(newExpenseName.getText().toString());
//        newExpense.setAmount(Long.parseLong(newExpenseAmount.getText().toString()));
//        viewModel.addExpense(newExpense);
//        newExpenseAmount.setText("");
//        newExpenseName.setText("");
//      });
//
//      final CategoryViewModel categoryViewModel = ViewModelProviders.of(getActivity())
//          .get(CategoryViewModel.class);
//      categoryViewModel.getCategory().observe(this, categories -> {
//        final Spinner expenseSpinner = view.findViewById(R.id.category_spinner);
//        SpinnerAdapter spinnerAdapter = new ArrayAdapter<>(context,
//            android.R.layout.simple_spinner_item, categories);
//        expenseSpinner.setAdapter(spinnerAdapter);
//      });
    return view;
  }


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }
}

