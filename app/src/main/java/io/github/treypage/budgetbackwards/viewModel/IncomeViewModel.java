package io.github.treypage.budgetbackwards.viewModel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import io.github.treypage.budgetbackwards.model.database.BudgetDatabase;
import io.github.treypage.budgetbackwards.model.entity.Income;
import java.util.List;

public class IncomeViewModel extends AndroidViewModel {

  private LiveData<List<Income>> income;

  public IncomeViewModel(@NonNull Application application) {
    super(application);
    BudgetDatabase db = BudgetDatabase.getInstance(application);
    income = db.getIncomeDao().getAll();
  }

  public LiveData<List<Income>> getIncome() {
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
}
