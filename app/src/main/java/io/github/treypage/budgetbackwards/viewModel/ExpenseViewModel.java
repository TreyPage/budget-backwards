package io.github.treypage.budgetbackwards.viewModel;


import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import io.github.treypage.budgetbackwards.model.database.BudgetDatabase;
import io.github.treypage.budgetbackwards.model.entity.Expense;
import java.util.List;

public class ExpenseViewModel extends AndroidViewModel {

  private LiveData<List<Expense>> expenses;

  public ExpenseViewModel(@NonNull Application application) {
    super(application);
  }

  public LiveData<List<Expense>> getExpenses() {
    expenses = BudgetDatabase.getInstance(getApplication()).getExpenseDao().getAll();
    return expenses;
  }

  public void addExpense(final Expense expense) {
    new Thread(() -> {
     BudgetDatabase db = BudgetDatabase.getInstance(getApplication());
     db.getExpenseDao().insert(expense);
    }).start();
  }
}
