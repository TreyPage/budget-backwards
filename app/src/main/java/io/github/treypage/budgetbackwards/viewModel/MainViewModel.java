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
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;

public class MainViewModel extends AndroidViewModel implements LifecycleObserver {

  private CompositeDisposable pending = new CompositeDisposable();

  public MainViewModel(@NonNull Application application) {
    super(application);
  }

  @OnLifecycleEvent(Event.ON_STOP)
  public void disposePending() {
    pending.clear();
  }

  public LiveData<List<Category>> getCategory() {
    return BudgetDatabase.getInstance(getApplication())
        .getCategoryDao().getAll();
  }

  public LiveData<Long> getSumExpenses() {
    return BudgetDatabase.getInstance(getApplication())
        .getExpenseDao().getSumExpenses();
  }

  public LiveData<Double> getOneIncome() {
    return BudgetDatabase.getInstance(getApplication())
        .getIncomeDao().getOneIncome();
  }

  public void addIncome(final Income income) {
    new Thread(() -> {
      BudgetDatabase db = BudgetDatabase.getInstance(getApplication());
      db.getIncomeDao().insert(income);
    }).start();
  }

  public LiveData<List<Expense>> getExpenses() {
    return BudgetDatabase.getInstance(getApplication()).getExpenseDao().getAll();
  }

  public LiveData<List<Income>> getIncome() {
    return BudgetDatabase.getInstance(getApplication()).getIncomeDao().getAll();
  }

  public void setIncome(
      LiveData<List<Income>> income) {
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

  public void deleteExpense(final Expense expense) {
    new Thread(() -> {
      BudgetDatabase db = BudgetDatabase.getInstance(getApplication());
      db.getExpenseDao().delete(expense);
      categoryPercentAll();
    }).start();
  }

  public void updateCategory(final Category category) {
    new Thread(() -> {
      BudgetDatabase db = BudgetDatabase.getInstance(getApplication());
      db.getCategoryDao().update(category);
    }).start();
  }


  public void categoryPercentAll() {
    new Thread(() -> {
      List<Double> percent = getPercent();
      for (int i = 0; i < percent.size(); i++) {
        Category category = new Category();
        category.setId(i);
        category.setName(Category.Title.values()[i].toString());
        if (percent.get(i) == null) {
          category.setPercent(0);
        } else {
          category.setPercent(percent.get(i));
        }
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
        category.setPayout(payout);
        category.setName(Category.Title.values()[i].toString());
        category.setId(i);
        updateCategory(category);
      }
    }).start();
  }
}

