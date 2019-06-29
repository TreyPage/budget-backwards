package io.github.treypage.budgetbackwards.viewModel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.OnLifecycleEvent;
import io.github.treypage.budgetbackwards.model.database.BudgetDatabase;
import io.github.treypage.budgetbackwards.model.entity.Category;
import io.github.treypage.budgetbackwards.model.entity.Expense;
import io.github.treypage.budgetbackwards.model.entity.Income;
import io.github.treypage.budgetbackwards.model.entity.Quotes;
import io.github.treypage.budgetbackwards.model.service.QuotesService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class MainViewModel extends AndroidViewModel implements LifecycleObserver {

  private LiveData<List<Income>> income;
  Quotes quotes = new Quotes();
  private CompositeDisposable pending = new CompositeDisposable();

  @OnLifecycleEvent(Event.ON_STOP)
  public void disposePending() {
    pending.clear();
  }

  public MainViewModel(@NonNull Application application) {
    super(application);
  }

  public LiveData<List<Category>> getCategory() {
    return BudgetDatabase.getInstance(getApplication())
        .getCategoryDao().getAll();
  }

  public LiveData<Long> getSumExpenses() {
    return BudgetDatabase.getInstance(getApplication())
        .getExpenseDao().getSumExpenses();
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

  public List<Double> getPercent() {
    return BudgetDatabase.getInstance(getApplication()).getExpenseDao()
        .getPercent();
  }

  public void addExpense(final Expense expense) {
    new Thread(() -> {
      BudgetDatabase db = BudgetDatabase.getInstance(getApplication());
      db.getExpenseDao().insert(expense);
      categoryPercentAll();
    }).start();
  }

  public void updateCategory(final Category category) {
    new Thread(() -> {
      BudgetDatabase db = BudgetDatabase.getInstance(getApplication());
      db.getCategoryDao().update(category);
    }).start();
  }

  public void newQuote() {
    pending.add(
        QuotesService.getInstance().starWarsQuote()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(aQuote -> this.quotes.setSwQuote(aQuote))

    );
  }

  public void categoryPercentAll() {
    new Thread(() -> {
      List<Double> percent = getPercent();
      for (int i = 0; i < percent.size(); i++) {
        Category category = new Category();
        category.setId(i);
        category.setName(Category.Title.values()[i].toString());
        category.setPercent(percent.get(i));
        updateCategory(category);
      }
    }).start();
  }

  public void incomeMath(double newIncome) {
    new Thread(() -> {
      List<Double> list = getPercent();
      for (int i = 0; i < list.size(); i++) {
        Category category = new Category();
        double percent = getPercent().get(i);
        double payout = newIncome * (percent / 100);
        category.setPercent(percent);
        category.setPayout((long) payout);
        category.setName(Category.Title.values()[i].toString());
        category.setId(i);
        updateCategory(category);
      }
    }).start();
  }
}

