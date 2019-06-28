package io.github.treypage.budgetbackwards.viewModel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import io.github.treypage.budgetbackwards.model.database.BudgetDatabase;
import io.github.treypage.budgetbackwards.model.entity.Category;
import io.github.treypage.budgetbackwards.model.entity.Expense;
import io.github.treypage.budgetbackwards.model.entity.Income;
import java.util.List;

public class MainViewModel extends AndroidViewModel {

  private LiveData<List<Income>> income;

  public MainViewModel(@NonNull Application application) {
    super(application);
  }

  public LiveData<List<Category>> getCategory() {
    return BudgetDatabase.getInstance(getApplication())
        .getCategoryDao().getAll();
  }

  public LiveData<Long> getAllIncome() {
    return BudgetDatabase.getInstance(getApplication())
        .getCategoryDao().getAllIncome();
  }

  public LiveData<Long> getAllExpenses() {
    return BudgetDatabase.getInstance(getApplication())
        .getCategoryDao().getAllExpenses();
  }

  public LiveData<Long> getSumExpenses() {
    return BudgetDatabase.getInstance(getApplication())
        .getCategoryDao().getSumExpenses();
  }

  public LiveData<List<Income>> getIncome() {
    income = BudgetDatabase.getInstance(getApplication()).getIncomeDao().getAll();
    return income;
  }

  public void setIncome(
      LiveData<List<Income>> income) {
    this.income = income;
  }

  public void addIncome(final Income income) {
    new Thread(() -> {
      BudgetDatabase db = BudgetDatabase.getInstance(getApplication());
      db.getIncomeDao().insert(income);
    }).start();
  }

  public LiveData<List<Expense>> getExpenses() {
    return BudgetDatabase.getInstance(getApplication()).getExpenseDao()
        .getAll();
  }

  public void addExpense(final Expense expense) {
    new Thread(() -> {
      BudgetDatabase db = BudgetDatabase.getInstance(getApplication());
      db.getExpenseDao().insert(expense);
    }).start();
  }

  public void updateCategory(final Category category) {
    new Thread(() -> {
      BudgetDatabase db = BudgetDatabase.getInstance(getApplication());
      db.getCategoryDao().update(category);
    }).start();
  }

  public void categoryPercent(Category.Title title, long expense) {
    Category category = new Category();
    long sumExpenses;
    long percent;
    try {
      sumExpenses = getSumExpenses().getValue();
      percent = (expense / sumExpenses) * 100;
    } catch (NullPointerException e) {
      // TODO this should be ignored after the nullException is fixed
      percent = 100;
    }
    category.setPercent((double) percent);
    category.setId(title.ordinal());
    category.setName(title.toString());
    updateCategory(category);
  }

}
