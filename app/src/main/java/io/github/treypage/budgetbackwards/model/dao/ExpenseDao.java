package io.github.treypage.budgetbackwards.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.github.treypage.budgetbackwards.model.entity.Expense;
import java.util.List;

@Dao
public interface ExpenseDao {

  @Insert
  long insert(Expense expense);

  @Query("SELECT * FROM expense")
  LiveData<List<Expense>> getAll();

  @Query("SELECT sum(`amount`) FROM expense")
  LiveData<Long> getExpenseTotal();

}
