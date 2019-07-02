package io.github.treypage.budgetbackwards.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import io.github.treypage.budgetbackwards.model.entity.Expense;
import java.util.List;

@Dao
public interface ExpenseDao {

  @Insert
  long insert(Expense expense);

  @Delete
  int delete(Expense title);

  @Delete
  int delete(Expense... expenses);

  @Query("SELECT * FROM expense")
  LiveData<List<Expense>> getAll();

  @Query("SELECT amount FROM expense")
  LiveData<Long> getAllExpenses();

  @Query("SELECT SUM(amount) FROM expense")
  LiveData<Long> getSumExpenses();

  @Query("SELECT 100.0 * SUM(IFNULL(ex.amount, 0))/t.total FROM category c left join expense ex ON ex.category_id = c.id CROSS JOIN (SELECT SUM(amount) AS total FROM Expense) t GROUP BY c.id")
  List<Double> getPercent();
}
